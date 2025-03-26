package repository;

import entity.Application;
import entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ApplicationRepository extends CrudRepository<Application, Long> {
    /**
     * Находит все приложения, которые загружены у конкретного пользователя
     * @param user пользователь программы
     * */
    @Query("FROM Application WHERE application.user = :user")
    List<Application> findByUser(User user);

    /**
     * Находит программу по наименованию
     * @param appName название программы
     * */
    Application findByName(String appName);
}
