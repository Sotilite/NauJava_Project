package ru.alexander.NauJava.service;

import ru.alexander.NauJava.entity.Application;

import java.util.List;

public interface ApplicationService {
    /**
     * Получение списка всех программ из репозитория
     * */
    List<Application> allApplications();
}
