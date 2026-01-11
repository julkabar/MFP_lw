package yyb.julkabar.persistence.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import yyb.julkabar.core.domain.Comment;

import java.util.List;

@Repository
public interface CommentJpaRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByBookIdOrderByCreatedAtDesc(Long bookId);
    List<Comment> findByAuthorOrderByCreatedAtDesc(String author);
}
