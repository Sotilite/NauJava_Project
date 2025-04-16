package ru.alexander.NauJava.service;

import ru.alexander.NauJava.entity.Report;

public interface ReportService {
    /**
     * Создание отчета, возвращает его идентификатор после добавления в БД
     * */
    Long createReport();

    /**
     * Получение содержимого отчета по идентификатору
     * @param id уникальный идентификатор отчета
     * */
    Report findReportById(Long id);

    /**
     * Обновление отчета
     * @param id идентификатор отчета
     * @param status статус отчета
     * @param content содержимое отчета
      * */
    void updateReport(Long id, String status, String content) throws Exception;

    /**
     * Запускает формирование отчета ассинхронно
     * @param reportId идентификатор отчета, для которого нужно запустить процесс формирования
     * */
    void startReportGeneration(Long reportId);
}
