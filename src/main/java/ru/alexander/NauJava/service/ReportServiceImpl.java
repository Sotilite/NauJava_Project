package ru.alexander.NauJava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.alexander.NauJava.entity.Report;
import ru.alexander.NauJava.enums.Status;
import ru.alexander.NauJava.repository.ReportRepository;

import java.util.HashMap;
import java.util.Map;
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
        reportRepository.save(newReport);
        return newReport.getId();
    }

    @Override
    public Report findReportById(Long id) {
        var report = reportRepository.findById(id);
        if (report.isPresent()) {
            return report.get();
        }
        var newReportId = createReport();
        return findReportById(newReportId);
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
    public void startReportGeneration(Long reportId) {
        var startTime = System.currentTimeMillis();
        var generationReport = reportRepository.findById(reportId);

        var future = CompletableFuture.supplyAsync(() -> {
            try {
                var data = new HashMap<String, String>();
                var computeUserCount = new Thread(() -> {
                    calculateNumberOfUsers(data);
                });
                var computeApplicationList = new Thread(() -> {
                    generateApplicationList(data);
                });
                computeUserCount.start();
                computeApplicationList.start();

                computeUserCount.join();
                computeApplicationList.join();

                synchronized (data) {
                    data.put("{{generalTime}}", String.valueOf(System.currentTimeMillis() - startTime));
                }
                return getCompletedTable(data);
            } catch (InterruptedException interruptedEx) {
                if (generationReport.isPresent()) {
                    try {
                        updateReport(generationReport.get().getId(), String.valueOf(Status.ERROR), "");
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
                throw new RuntimeException();
            }
        });

        future.thenAccept(result -> {
            if (generationReport.isPresent()) {
                try {
                    updateReport(generationReport.get().getId(), String.valueOf(Status.COMPLETED), result);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    private synchronized void calculateNumberOfUsers(Map<String, String> data) {
        var startTimeForComputingUserCount = System.currentTimeMillis();
        var numberOfUsers = userService.countUsers();
        data.put("{{userNumber}}", String.valueOf(numberOfUsers));
        data.put("{{userCountTime}}", String.valueOf(System.currentTimeMillis() - startTimeForComputingUserCount));
    }

    private synchronized void generateApplicationList(Map<String, String> data) {
        var startTimeForComputingAppList = System.currentTimeMillis();
        var applications = applicationService.allApplications();
        var appList = new StringBuilder();
        for (var app : applications) {
            appList.append(String.format("<tr><td>%d</td><td>%s</td><td>%s</td><td>%s</td><td>%s</td></tr>",
                    app.getId(), app.getName(), app.getPathToApplication(), app.getVersion(), app.getUser().getLogin()));
        }
        data.put("{{appList}}", appList.toString());
        data.put("{{appListTime}}", String.valueOf(System.currentTimeMillis() - startTimeForComputingAppList));
    }

    private String getCompletedTable(Map<String, String> data) {
        var table = Report.table;
        table = table.replace("{{userNumber}}", data.get("{{userNumber}}"));
        table = table.replace("{{userCountTime}}", data.get("{{userCountTime}}"));
        table = table.replace("{{appList}}", data.get("{{appList}}"));
        table = table.replace("{{appListTime}}", data.get("{{appListTime}}"));
        table = table.replace("{{generalTime}}", data.get("{{generalTime}}"));
        return table;
    }
}
