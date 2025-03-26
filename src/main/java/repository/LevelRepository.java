package repository;

import entity.Application;
import entity.Level;
import org.springframework.data.repository.CrudRepository;

public interface LevelRepository extends CrudRepository<Level, Long> {
    /**
     * Находит уровень по наименованию
     * @param levelName название программы
     * */
    Level findByName(String levelName);

    /**
     * Удаляет уровень по его наименованию
     * @param levelName наимменование уровня
     * */
    void deleteByName(String levelName);
}
