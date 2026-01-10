package yyb.julkabar.web;

import io.javalin.Javalin;
import io.javalin.http.Context;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import yyb.julkabar.core.domain.Book;
import yyb.julkabar.core.domain.PageRequest;
import yyb.julkabar.core.port.CatalogRepositoryPort;

import java.util.Map;

public class BooksApiController {

    private static final Logger log = LoggerFactory.getLogger(BooksApiController.class);

    private final CatalogRepositoryPort bookRepo;

    public BooksApiController(CatalogRepositoryPort bookRepo) {
        this.bookRepo = bookRepo;
    }

    public void registerRoutes(Javalin app) {
        // REST API — JSON only
        app.get("/api/books", this::getBooks);
        app.post("/api/books", this::addBook);
    }

    private void getBooks(Context ctx) {
        try {
            String q = ctx.queryParam("q");
            if (q == null) q = "";

            int page = ctx.queryParamAsClass("page", Integer.class).getOrDefault(0);
            int size = ctx.queryParamAsClass("size", Integer.class).getOrDefault(10);

            var result = bookRepo.search(q, new PageRequest(page, size));
            ctx.json(result);

        } catch (Exception e) {
            log.error("DB error in GET /api/books", e);
            ctx.status(500).json(Map.of("error", "Database error"));
        }
    }

    private void addBook(Context ctx) {
        try {
            Book book = ctx.bodyAsClass(Book.class);

            if (book.getTitle() == null || book.getTitle().isBlank() ||
                    book.getAuthor() == null || book.getAuthor().isBlank()) {
                ctx.status(400).json(Map.of("error", "title and author required"));
                return;
            }

            if (book.getPubYear() <= 0) {
                ctx.status(400).json(Map.of("error", "invalid pubYear"));
                return;
            }

            Book saved = bookRepo.add(
                    book.getTitle().trim(),
                    book.getAuthor().trim(),
                    book.getPubYear()
            );
            ctx.status(201).json(saved);

        } catch (Exception e) {
            log.error("DB error in POST /api/books", e);
            ctx.status(500).json(Map.of("error", "Database error"));
        }
    }

}

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import yyb.julkabar.core.domain.Book;
//import yyb.julkabar.core.domain.Page;
//import yyb.julkabar.core.domain.PageRequest;
//import yyb.julkabar.core.port.CatalogRepositoryPort;
//
//@RestController
//@RequestMapping("/api/books")
//public class BooksApiController {
//
//    private static final Logger log = LoggerFactory.getLogger(BooksApiController.class);
//
//    private final CatalogRepositoryPort bookRepo;
//    public BooksApiController(CatalogRepositoryPort bookRepo) {
//        this.bookRepo = bookRepo;
//    }
//
//    @GetMapping
//    public ResponseEntity<Page<Book>> getBooks(
//            @RequestParam(required = false) String q,
//            @RequestParam(defaultValue = "0") int page,
//            @RequestParam(defaultValue = "10") int size) {
//        try {
//            Page<Book> result = bookRepo.search(q, new PageRequest(page, size));
//            return ResponseEntity.ok(result);
//        } catch (Exception e) {
//            log.error("DB error while GET /api/books", e);
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//        }
//    }
//
//    @PostMapping
//    public ResponseEntity<?> addBook(@RequestBody Book book) {
//
//        try {
//            if (book.getTitle() == null || book.getTitle().isBlank()
//                    || book.getAuthor() == null || book.getAuthor().isBlank()) {
//                return ResponseEntity.badRequest().body("title & author required");
//            }
//            if (book.getPubYear() <= 0) {
//                return ResponseEntity.badRequest().body("invalid pubYear");
//            }
//
//            Book saved = bookRepo.add(
//                    book.getTitle().trim(),
//                    book.getAuthor().trim(),
//                    book.getPubYear()
//            );
//
//            return ResponseEntity.status(HttpStatus.CREATED).body(saved);
//
//        } catch (Exception e) {
//            log.error("500: DB error while POST /api/books", e);
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("DB error");
//        }
//    }
//}
