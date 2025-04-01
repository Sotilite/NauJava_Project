package ru.alexander.NauJava.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.alexander.NauJava.entity.Level;

@RepositoryRestResource(path = "levels")
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
