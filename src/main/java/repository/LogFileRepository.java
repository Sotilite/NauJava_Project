package repository;

import entity.Application;
import entity.LogFile;
import org.springframework.data.repository.CrudRepository;

public interface LogFileRepository extends CrudRepository<LogFile, Long> {
    /**
     * Находит файл с логами по названию
     * @param logFileName название файла с логами
     * */
    LogFile findByName(String logFileName);
}
