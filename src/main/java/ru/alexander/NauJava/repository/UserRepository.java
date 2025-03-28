package ru.alexander.NauJava.repository;

import org.springframework.data.repository.CrudRepository;
import ru.alexander.NauJava.entity.User;

public interface UserRepository extends CrudRepository<User, Long> {
    /**
     * Находит пользователя по логину
     * @param login логин пользователя
     * */
    User findByLogin(String login);
}
