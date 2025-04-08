package ru.alexander.NauJava.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.alexander.NauJava.entity.User;

@RepositoryRestResource(path = "users")
public interface UserRepository extends CrudRepository<User, Long> {
    /**
     * Находит пользователя по логину
     * @param login логин пользователя
     * */
    User findByLogin(String login);
}
