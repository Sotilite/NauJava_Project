package ru.alexander.NauJava;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import ru.alexander.NauJava.entity.Report;
import ru.alexander.NauJava.enums.Status;
import ru.alexander.NauJava.repository.ReportRepository;
import ru.alexander.NauJava.service.ApplicationService;
import ru.alexander.NauJava.service.ReportServiceImpl;
import ru.alexander.NauJava.service.UserService;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ReportServiceTest {
    @InjectMocks
    private ReportServiceImpl reportService;

    @Mock
    private UserService userService;

    @Mock
    private ApplicationService applicationService;

    @Mock
    private ReportRepository reportRepository;

    private Report defaultReport;
    private Report generatedReport;

    @BeforeEach
    public void setUp() {
        defaultReport = new Report("CREATED", "");
        defaultReport.setId(999L);
        generatedReport = new Report("COMPLETED", "information");
        generatedReport.setId(999L);
    }

    @Test
    void testCreatingReport() {
        when(reportRepository.save(any(Report.class))).thenReturn(defaultReport);

        var reportId = reportService.createReport();

        Assertions.assertEquals(defaultReport.getId(), reportId);
        verify(reportRepository, times(1)).save(any(Report.class));
    }

    @Test
    void testFindingReportById() {
        when(reportRepository.save(any(Report.class))).thenReturn(defaultReport);
        when(reportRepository.findById(any(Long.class))).thenReturn(Optional.of(defaultReport));

        var reportId = reportService.createReport();
        var createdReport = reportService.findReportById(reportId);

        Assertions.assertNotNull(createdReport);
        Assertions.assertEquals(reportId, createdReport.getId());
        verify(reportRepository, times(1)).save(any(Report.class));
        verify(reportRepository, times(1)).findById(any(Long.class));
    }

    @Test
    void testUnsuccessfulUpdatingReport() {
        when(reportRepository.findById(any(Long.class))).thenReturn(null);

        Assertions.assertThrows(Exception.class, () -> {
            reportService.updateReport(
                    defaultReport.getId(),
                    String.valueOf(Status.COMPLETED),
                    "This is a new content to test updating method"
            );
        });
        verify(reportRepository, times(1)).findById(any(Long.class));
    }

    @Test
    void testDeletingReport() {
        when(reportRepository.save(any(Report.class))).thenReturn(defaultReport);
        when(reportRepository.findById(any(Long.class))).thenReturn(Optional.of(defaultReport));

        var reportId = reportService.createReport();
        var createdReport = reportService.findReportById(reportId);
        reportService.deleteReport(createdReport);

        verify(reportRepository, times(1)).save(any(Report.class));
        verify(reportRepository, times(1)).findById(any(Long.class));
        verify(reportRepository, times(1)).delete(any(Report.class));
    }

    @Test
    void testStartingReportGeneration() {
        when(reportRepository.save(any(Report.class)))
                .thenReturn(defaultReport)
                .thenReturn(generatedReport);
        when(reportRepository.findById(any(Long.class)))
                .thenReturn(Optional.of(defaultReport))
                .thenReturn(Optional.of(defaultReport))
                .thenReturn(Optional.of(generatedReport));
        when(userService.countUsers()).thenReturn(10L);
        when(applicationService.allApplications()).thenReturn(List.of());

        var reportId = reportService.createReport();
        reportService.startReportGeneration(reportId);
        Assertions.assertDoesNotThrow(() -> {
            Thread.sleep(3000);
        });

        var generatedReport = reportService.findReportById(reportId);
        var expectedStatus = String.valueOf(Status.COMPLETED);
        Assertions.assertEquals(expectedStatus, generatedReport.getStatus());
        Assertions.assertNotNull(generatedReport.getContent());
        verify(reportRepository, times(2)).save(any(Report.class));
        verify(reportRepository, times(3)).findById(any(Long.class));
        verify(userService, times(1)).countUsers();
        verify(applicationService, times(1)).allApplications();
    }
}
