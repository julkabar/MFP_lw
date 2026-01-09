package yyb.julkabar.config;

import yyb.julkabar.persistence.jbc.DbInit;
import yyb.julkabar.persistence.jbc.JdbcBookRepository;
import yyb.julkabar.persistence.jbc.JdbcCommentRepository;
import yyb.julkabar.core.port.CatalogRepositoryPort;
import yyb.julkabar.core.port.CommentRepositoryPort;
import yyb.julkabar.core.service.CommentService;

public class Beans {
    private static CommentService commentService;

    public static void init() {
        DbInit.init();

        var repo = new JdbcCommentRepository();
        commentService = new CommentService(repo);
    }

    private static final CatalogRepositoryPort bookRepo = new JdbcBookRepository();
    private static final CommentRepositoryPort commentRepo = new JdbcCommentRepository();

    public static CatalogRepositoryPort getBookRepo() {
        return bookRepo;
    }

    public static CommentRepositoryPort getCommentRepo() {
        return commentRepo;
    }

    public static CommentService commentService() { return commentService; }
}
