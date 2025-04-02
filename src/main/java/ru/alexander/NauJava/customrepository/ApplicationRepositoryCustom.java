package ru.alexander.NauJava.customrepository;

import ru.alexander.NauJava.entity.Application;
import ru.alexander.NauJava.entity.User;

import java.util.List;

public interface ApplicationRepositoryCustom {
    /**
     * Находит все приложения, которые загружены у конкретного пользователя
     * @param user пользователь программы
     * */
    List<Application> findAllByUser(User user);
}
