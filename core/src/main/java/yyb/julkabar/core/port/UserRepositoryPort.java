package yyb.julkabar.core.port;

import yyb.julkabar.core.domain.User;

import java.util.Optional;

public interface UserRepositoryPort {
    Optional<User> findById(long id);
    Optional<User> findByUsername(String username);
    User save(User user);
}
