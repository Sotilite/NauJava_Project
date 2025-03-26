package repository;

import entity.Application;
import entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    /**
     * Находит пользователя по логину
     * @param login логин пользователя
     * */
    User findByLogin(String login);
}
