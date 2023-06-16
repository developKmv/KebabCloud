package ru.dev.A1.A1.data;

import org.springframework.data.repository.CrudRepository;
import ru.dev.A1.A1.models.User;

public interface UserRepository extends CrudRepository<User,Long> {
    User findByUsername(String username);
}
