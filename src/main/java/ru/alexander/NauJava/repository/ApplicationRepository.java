package ru.alexander.NauJava.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.alexander.NauJava.entity.Application;
import ru.alexander.NauJava.entity.User;

import java.util.List;

public interface ApplicationRepository extends CrudRepository<Application, Long> {
    /**
     * Находит все приложения, которые загружены у конкретного пользователя
     * @param user пользователь программы
     * */
    @Query(value = "FROM Application app WHERE app.user = :user")
    List<Application> findAllByUser(User user);

    /**
     * Находит программу по названию
     * @param appName название программы
     * */
    Application findByName(String appName);
}
