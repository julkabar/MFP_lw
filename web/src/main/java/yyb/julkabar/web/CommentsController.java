package yyb.julkabar.web;

import io.javalin.Javalin;
import io.javalin.http.Context;
import yyb.julkabar.core.domain.PageRequest;
import yyb.julkabar.core.port.CatalogRepositoryPort;
import yyb.julkabar.core.port.CommentRepositoryPort;

import java.util.Map;

public class CommentsController {

    private final CommentRepositoryPort commentRepo;
    private final CatalogRepositoryPort bookRepo;

    public CommentsController(CommentRepositoryPort commentRepo, CatalogRepositoryPort bookRepo) {
        this.commentRepo = commentRepo;
        this.bookRepo = bookRepo;
    }

    public void registerRoutes(Javalin app) {

        // --- REST API ---
        app.get("/api/comments", this::getComments);
        app.post("/api/comments", this::addComment);
        app.delete("/api/comments/{commentId}", this::deleteComment);

        // --- сторінка з фронтом ---
        app.get("/comments", ctx -> ctx.redirect("/book-comments.html"));
    }

    /**
     * Повертає JSON зі списком коментарів до книги
     */
    private void getComments(Context ctx) {
        Long bookId = ctx.queryParamAsClass("bookId", Long.class).getOrDefault(null);
        if (bookId == null) {
            ctx.status(400).result("Missing bookId");
            return;
        }

        var book = bookRepo.findById(bookId);
        var comments = commentRepo.list(bookId, null, null, new PageRequest(0, 20)).getItems();

        ctx.json(Map.of(
                "book", book,
                "comments", comments
        ));
    }

    /**
     * Додає коментар (через POST /api/comments)
     */
    private void addComment(Context ctx) {
        Long bookId = ctx.formParamAsClass("bookId", Long.class).getOrDefault(null);
        String author = ctx.formParam("author");
        String text = ctx.formParam("text");

        if (bookId == null || author == null || text == null ||
                author.isBlank() || text.isBlank()) {
            ctx.status(400).result("bookId, author and text required");
            return;
        }
        System.out.println("Adding comment to bookId = " + bookId + " by " + author);

        commentRepo.add(bookId, author.trim(), text.trim());
        ctx.status(201).json(Map.of("status", "ok"));
    }

    /**
     * Видаляє коментар (через DELETE /api/comments/:commentId)
     */
    private void deleteComment(Context ctx) {
        Long bookId = ctx.queryParamAsClass("bookId", Long.class).getOrDefault(null);
        Long commentId = ctx.pathParamAsClass("commentId", Long.class).getOrDefault(null);

        if (bookId == null || commentId == null) {
            ctx.status(400).result("bookId and commentId required");
            return;
        }

        commentRepo.delete(bookId, commentId);
        ctx.status(200).json(Map.of("status", "deleted"));
    }
}

//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//import yyb.julkabar.core.domain.Comment;
//import yyb.julkabar.core.domain.PageRequest;
//import yyb.julkabar.core.port.CatalogRepositoryPort;
//import yyb.julkabar.core.port.CommentRepositoryPort;
//
//import java.util.List;
//
//@Controller
//@RequestMapping("/comments")
//public class CommentsController {
//
//    private final CatalogRepositoryPort bookRepo;
//    private final CommentRepositoryPort commentRepo;
//
//    public CommentsController(CatalogRepositoryPort bookRepo, CommentRepositoryPort commentRepo) {
//        this.bookRepo = bookRepo;
//        this.commentRepo = commentRepo;
//    }
//
//    @GetMapping
//    public String list(@RequestParam long bookId, Model model) {
//        var book = bookRepo.findById(bookId);
//        List<Comment> comments = commentRepo.list(bookId, null, null, new PageRequest(0, 20)).getItems();
//
//        model.addAttribute("book", book);
//        model.addAttribute("comments", comments);
//        return "book-comments";
//    }
//
//    @PostMapping
//    public String add(@RequestParam long bookId,
//                      @RequestParam String author,
//                      @RequestParam String text) {
//        if (!author.isBlank() && !text.isBlank()) {
//            commentRepo.add(bookId, author.trim(), text.trim());
//        }
//
//        return "redirect:/comments?bookId=" + bookId;
//    }
//
//    @PostMapping("/delete")
//    public String delete(@RequestParam long bookId,
//                         @RequestParam long commentId) {
//        commentRepo.delete(bookId, commentId);
//        return "redirect:/comments?bookId=" + bookId;
//    }
//}
