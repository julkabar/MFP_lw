package yyb.julkabar.infrastructure;

import yyb.julkabar.core.port.CatalogRepositoryPort;
import yyb.julkabar.core.port.CommentRepositoryPort;
import yyb.julkabar.persistence.jbc.DbInit;
import yyb.julkabar.persistence.jbc.JdbcBookRepository;
import yyb.julkabar.persistence.jbc.JdbcCommentRepository;

public class ApplicationInitializer {

    static {
        DbInit.init();
    }

    public static CatalogRepositoryPort createCatalogRepository() {
        return new JdbcBookRepository();
    }

    public static CommentRepositoryPort createCommentRepository() {
        return new JdbcCommentRepository();
    }
}
