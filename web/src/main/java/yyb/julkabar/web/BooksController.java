package yyb.julkabar.web;

import io.javalin.Javalin;
import io.javalin.http.Context;
import yyb.julkabar.core.domain.PageRequest;
import yyb.julkabar.core.port.CatalogRepositoryPort;

public class BooksController {

    private final CatalogRepositoryPort bookRepo;

    public BooksController(CatalogRepositoryPort bookRepo) {
        this.bookRepo = bookRepo;
    }

    public void registerRoutes(Javalin app) {

        app.get("/books", ctx -> ctx.redirect("/books.html"));
    }

    private void getBooks(Context ctx) {
        var books = bookRepo.search("", new PageRequest(0, 100)).getItems();
        ctx.json(books);
    }
}

//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.servlet.mvc.support.RedirectAttributes;
//import yyb.julkabar.core.domain.Book;
//import yyb.julkabar.core.domain.PageRequest;
//import yyb.julkabar.core.port.CatalogRepositoryPort;
//
//import java.util.List;
//
//@Controller
//@RequestMapping("/books")
//public class BooksController {
//
//    private final CatalogRepositoryPort bookRepo;
//
//    public BooksController(CatalogRepositoryPort bookRepo) {
//        this.bookRepo = bookRepo;
//    }
//
//    @GetMapping
//    public String getAllBooks(Model model) {
//        List<Book> books = bookRepo.search(null, new PageRequest(0, 20)).getItems();
//        model.addAttribute("books", books);
//        return "books";
//    }
//
//    @GetMapping("books/{id}")
//    public String getBook(@PathVariable long id, Model model) {
//        Book book = bookRepo.findById(id);
//        model.addAttribute("book", book);
//        return "book-comments";
//    }
//
//    @PostMapping
//    public String addBook(
//            @RequestParam String title,
//            @RequestParam String author,
//            @RequestParam int pubYear,
//            RedirectAttributes redirectAttributes
//    ) {
//        if (title == null || title.isBlank() ||
//                author == null || author.isBlank() ||
//                pubYear <= 0 || pubYear > 9999) {
//            redirectAttributes.addFlashAttribute("error", "title & author & pubYear required");
//            return "redirect:/books";
//        }
//
//        try {
//            bookRepo.add(title, author, pubYear);
//            return "redirect:/books";
//        } catch (Exception e) {
//            redirectAttributes.addFlashAttribute("error", "Cannot add book: " + e.getMessage());
//            return "redirect:/books";
//        }
//    }
//}
