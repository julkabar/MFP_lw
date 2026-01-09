package yyb.julkabar.core.port;

import yyb.julkabar.core.domain.Comment;
import yyb.julkabar.core.domain.Page;
import yyb.julkabar.core.domain.PageRequest;

import java.time.Instant;

public interface CommentRepositoryPort {
    void add(long bookId, String author, String text);
    Page<Comment> list(long bookId, String author, Instant since, PageRequest request);
    void delete(long bookId, long commentId);
}
