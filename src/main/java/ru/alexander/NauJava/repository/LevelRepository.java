package ru.alexander.NauJava.repository;

import org.springframework.data.repository.CrudRepository;
import ru.alexander.NauJava.entity.Level;

public interface LevelRepository extends CrudRepository<Level, Long> {
    /**
     * Находит уровень по названию
     * @param levelTitle название уровня
     * */
    Level findByTitle(String levelTitle);

    /**
     * Удаляет уровень по его названию
     * @param levelTitle названию уровня
     * */
    void deleteByTitle(String levelTitle);
}
