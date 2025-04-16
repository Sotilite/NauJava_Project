package ru.alexander.NauJava.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import ru.alexander.NauJava.entity.User;

import java.util.List;

public interface UserService extends UserDetailsService {
    /**
     * Добавляет нового пользоватля в репозиторий
     * @param  user пользователь
     * */
    void addUser(UserDetails user) throws Exception;

    /**
     * Находит пользователя по логину
     * @param login логин пользователя
     * */
    User findUserByLogin(String login);

    /**
     * Подсчитывает всех пользователей
     * */
    Long countUsers();

    /**
     * Возвращает всех пользователей системы
     * */
    List<User> allUsers();
}
