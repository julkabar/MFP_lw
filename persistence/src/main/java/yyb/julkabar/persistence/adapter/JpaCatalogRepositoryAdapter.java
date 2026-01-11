package yyb.julkabar.persistence.adapter;

import org.springframework.stereotype.Repository;
import yyb.julkabar.core.domain.Book;
import yyb.julkabar.core.domain.Page;
import yyb.julkabar.core.domain.PageRequest;
import yyb.julkabar.core.port.CatalogRepositoryPort;
import yyb.julkabar.persistence.repo.BookJpaRepository;

@Repository
public class JpaCatalogRepositoryAdapter implements CatalogRepositoryPort {

    private final BookJpaRepository repo;

    public JpaCatalogRepositoryAdapter(BookJpaRepository repo) {
        this.repo = repo;
    }

    @Override
    public Page<Book> search(String query, PageRequest request) {

        var pageable = org.springframework.data.domain.PageRequest.of(
                request.getPage(),
                request.getSize()
        );

        var page = repo.findAll(pageable);

        return new Page<>(
                page.getContent(),
                request,
                page.getTotalElements()
        );

    }

    @Override
    public Book findById(long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public Book add(String title, String author, int pubYear) {

        Book book = new Book();
        book.setTitle(title);
        book.setAuthor(author);
        book.setPubYear(pubYear);

        return repo.save(book);
    }
}
