package ru.alexander.NauJava.service;

import ru.alexander.NauJava.entity.Level;

public interface LevelService {
    /**
     * Находит уровень по его названию
     * @param levelTitle название уровня в формате upperCase
     * */
    Level findLevelByTitle(String levelTitle);

    /**
     * Удаляет уровень по его названию
     * @param levelTitle название уровня
     * */
    void deleteLevelByTitle(String levelTitle);
}
