package ru.alexander.NauJava.repository;

import org.springframework.data.repository.CrudRepository;
import ru.alexander.NauJava.entity.LogFile;

public interface LogFileRepository extends CrudRepository<LogFile, Long> {
    /**
     * Находит файл с логами по названию
     * @param logFileName название файла с логами
     * */
    LogFile findByName(String logFileName);
}
