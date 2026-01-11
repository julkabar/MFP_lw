package yyb.julkabar.persistence.adapter;

import org.springframework.stereotype.Repository;
import yyb.julkabar.core.domain.Book;
import yyb.julkabar.core.domain.Comment;
import yyb.julkabar.core.domain.Page;
import yyb.julkabar.core.domain.PageRequest;
import yyb.julkabar.core.port.CommentRepositoryPort;
import yyb.julkabar.persistence.repo.CommentJpaRepository;

import java.time.Instant;
import java.util.List;

@Repository
public class JpaCommentRepositoryAdapter implements CommentRepositoryPort {
    private final CommentJpaRepository repo;

    public JpaCommentRepositoryAdapter(CommentJpaRepository repo) {
        this.repo = repo;
    }

    @Override
    public void add(Book book, String author, String text) {
        Comment comment = new Comment(book, author, text, Instant.now());
        repo.save(comment);
    }

    @Override
    public Page<Comment> list(long bookId, String author, Instant since, PageRequest request) {

        List<Comment> all = repo.findByBookIdOrderByCreatedAtDesc(bookId);

        List<Comment> filtered = all;

        int from = request.getPage() * request.getSize();
        int to = Math.min(from + request.getSize(), filtered.size());
        List<Comment> content = from >= filtered.size() ? List.of() : filtered.subList(from, to);

        int totalPages = (int) Math.ceil((double) filtered.size() / request.getSize());

        return new Page<>(content, request, totalPages);
    }

    @Override
    public void delete(long bookId, long commentId) {
        repo.deleteById(commentId);
    }

    @Override
    public List<Comment> findByAuthor(String author) {
        return repo.findByAuthorOrderByCreatedAtDesc(author);
    }

}
