package yyb.julkabar.persistence.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import yyb.julkabar.core.domain.Book;

@Repository
public interface BookJpaRepository extends JpaRepository<Book, Long> {

}
