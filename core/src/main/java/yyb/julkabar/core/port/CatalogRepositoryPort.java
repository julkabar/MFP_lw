package yyb.julkabar.core.port;

import yyb.julkabar.core.domain.Book;
import yyb.julkabar.core.domain.Page;
import yyb.julkabar.core.domain.PageRequest;

public interface CatalogRepositoryPort {
    Page<Book> search(String query, PageRequest request);
    Book findById(long id);
    public Book add(String title, String author, int pubYear);
}
