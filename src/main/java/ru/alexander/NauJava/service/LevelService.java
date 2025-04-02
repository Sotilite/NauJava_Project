package ru.alexander.NauJava.service;

public interface LevelService {
    /**
     * Удаляет уровень по его названию
     * @param levelTitle название уровня
     * */
    void deleteLevelByTitle(String levelTitle);
}
