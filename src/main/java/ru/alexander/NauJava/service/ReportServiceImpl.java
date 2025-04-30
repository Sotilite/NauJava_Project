package ru.alexander.NauJava.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.alexander.NauJava.entity.Report;
import ru.alexander.NauJava.entity.ReportContent;
import ru.alexander.NauJava.enums.Status;
import ru.alexander.NauJava.repository.ReportRepository;

import java.util.concurrent.CompletableFuture;

@Service
public class ReportServiceImpl implements ReportService {
    @Autowired
    ReportRepository reportRepository;

    @Autowired
    UserService userService;

    @Autowired
    ApplicationService applicationService;

    @Override
    public Long createReport() {
        var defaultStatus = String.valueOf(Status.CREATED);
        var newReport = new Report(defaultStatus, "");
        var savedReport = reportRepository.save(newReport);
        return savedReport.getId();
    }

    @Override
    public Report findReportById(Long id) {
        var report = reportRepository.findById(id);
        return report.orElse(null);
    }

    @Override
    public void updateReport(Long id, String status, String content) throws Exception {
        var updatingReport = findReportById(id);
        if (updatingReport == null) {
            throw new Exception("There isn't such report");
        }
        updatingReport.setStatus(status);
        updatingReport.setContent(content);
        reportRepository.save(updatingReport);
    }

    @Override
    public void deleteReport(Report report) {
        reportRepository.delete(report);
    }

    @Override
    public void startReportGeneration(Long reportId) {
        var startTime = System.currentTimeMillis();
        var generationReport = reportRepository.findById(reportId);
        var reportContent = new ReportContent();

        var future = CompletableFuture.supplyAsync(() -> {
            try {
                var computeUserCount = new Thread(() -> {
                    synchronized (reportContent) {
                        reportContent.setNumberOfUsers(userService.countUsers());
                        reportContent.setUserCountTime(System.currentTimeMillis() - startTime);
                    }
                });
                var computeApplicationList = new Thread(() -> {
                    synchronized (reportContent) {
                        reportContent.setApplications(applicationService.allApplications());
                        reportContent.setAppListTime(System.currentTimeMillis() - startTime);
                    }
                });
                computeUserCount.start();
                computeApplicationList.start();

                computeUserCount.join();
                computeApplicationList.join();

                synchronized (reportContent) {
                    reportContent.setGeneralTime(System.currentTimeMillis() - startTime);
                    ObjectMapper mapper = new ObjectMapper();
                    mapper.registerModule(new JavaTimeModule());
                    return mapper.writeValueAsString(reportContent);
                }
            } catch (Exception exception) {
                if (generationReport.isPresent()) {
                    try {
                        updateReport(generationReport.get().getId(), String.valueOf(Status.ERROR), "");
                    } catch (Exception updateException) {
                        throw new RuntimeException(updateException);
                    }
                }
                throw new RuntimeException();
            }
        });

        future.thenAccept(result -> {
            if (generationReport.isPresent()) {
                try {
                    updateReport(generationReport.get().getId(), String.valueOf(Status.COMPLETED), result);
                } catch (Exception updateException) {
                    throw new RuntimeException(updateException);
                }
            }
        });
    }
}
