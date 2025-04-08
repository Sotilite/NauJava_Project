package ru.alexander.NauJava.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.alexander.NauJava.entity.Application;
import ru.alexander.NauJava.entity.User;

import java.util.List;

@RepositoryRestResource(path = "applications")
public interface ApplicationRepository extends CrudRepository<Application, Long> {
    /**
     * Находит все приложения, которые загружены у конкретного пользователя по логину
     * @param login логин пользователя программы
     * */
    @Query(value = "FROM Application app WHERE app.user.login = :login")
    List<Application> findAllByUserLogin(String login);

    /**
     * Находит программу по названию
     * @param appName название программы
     * */
    Application findByName(String appName);
}
