package yyb.julkabar.persistence.adapter;

import org.springframework.stereotype.Repository;
import yyb.julkabar.core.domain.User;
import yyb.julkabar.core.port.UserRepositoryPort;
import yyb.julkabar.persistence.repo.UserJpaRepository;

import java.util.Optional;

@Repository
public class JpaUserRepositoryAdapter implements UserRepositoryPort {

    private final UserJpaRepository repo;

    public JpaUserRepositoryAdapter(UserJpaRepository repo) {
        this.repo = repo;
    }

    @Override
    public Optional<User> findById(long id) {
        return repo.findById(id);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return repo.findByUsername(username);
    }

    @Override
    public User save(User user) {
        return repo.save(user);
    }
}
