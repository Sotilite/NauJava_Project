package ru.alexander.NauJava;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.alexander.NauJava.entity.Report;
import ru.alexander.NauJava.enums.Status;
import ru.alexander.NauJava.service.ReportService;

@SpringBootTest
public class ReportServiceTest {
    @Autowired
    ReportService reportService;

    @Test
    void testCreatingReport() {
        var reportId = reportService.createReport();
        var createdReport = reportService.findReportById(reportId);

        var expectedStatus = String.valueOf(Status.CREATED);
        Assertions.assertEquals(expectedStatus, createdReport.getStatus());
        var expectedContent = "";
        Assertions.assertEquals(expectedContent, createdReport.getContent());

        reportService.deleteReport(createdReport);
    }

    @Test
    void testFindingReportById() {
        var reportId = reportService.createReport();
        var createdReport = reportService.findReportById(reportId);

        Assertions.assertEquals(reportId, createdReport.getId());

        reportService.deleteReport(createdReport);
    }

    @Test
    void testUpdatingReport() {
        //Negative test
        Long fakeReportId = 345342L;
        try {
            var fakeReport = Mockito.mock(Report.class);
            fakeReport.setId(fakeReportId);
            var newStatus = String.valueOf(Status.ERROR);
            var newContent = "This is a new content to test updating method";
            reportService.updateReport(fakeReport.getId(), newStatus, newContent);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            var unexistingReport = reportService.findReportById(fakeReportId);
            Assertions.assertNull(unexistingReport);
        }

        //Positive test
        try {
            var reportId = reportService.createReport();
            var newStatus = String.valueOf(Status.COMPLETED);
            var newContent = "This is a new content to test updating method";
            reportService.updateReport(reportId, newStatus, newContent);

            var updatedReport = reportService.findReportById(reportId);
            Assertions.assertEquals(newStatus, updatedReport.getStatus());
            Assertions.assertEquals(newContent, updatedReport.getContent());

            reportService.deleteReport(updatedReport);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }

    @Test
    void testDeletingReport() {
        var reportId = reportService.createReport();
        var createdReport = reportService.findReportById(reportId);
        reportService.deleteReport(createdReport);

        var deletedReport = reportService.findReportById(reportId);
        Assertions.assertNull(deletedReport);
    }

    @Test
    void testStartingReportGeneration() {
        try{
            var reportId = reportService.createReport();
            reportService.startReportGeneration(reportId);
            Thread.sleep(3000);

            var generatedReport = reportService.findReportById(reportId);
            var expectedStatus = String.valueOf(Status.COMPLETED);
            Assertions.assertEquals(expectedStatus, generatedReport.getStatus());
            Assertions.assertNotNull(generatedReport.getContent());

            reportService.deleteReport(generatedReport);
        } catch (InterruptedException exception) {
            System.out.println(exception.getMessage());
        }

    }
}
