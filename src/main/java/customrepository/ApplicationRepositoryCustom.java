package customrepository;

import entity.Application;
import entity.User;

import java.util.List;

public interface ApplicationRepositoryCustom {
    /**
     * Находит все приложения, которые загружены у конкретного пользователя
     * @param user пользователь программы
     * */
    List<Application> findByUser(User user);
}
