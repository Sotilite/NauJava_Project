package ru.alexander.NauJava.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.alexander.NauJava.entity.ReportContent;
import ru.alexander.NauJava.enums.Status;
import ru.alexander.NauJava.service.ReportService;

import java.util.Objects;

@Controller
@RequestMapping("/reports")
public class ReportController {
    @Autowired
    ReportService reportService;

    @GetMapping("/viewer")
    public String viewReports() {
        return "reportViewer";
    }

    @PostMapping("/create")
    public String createReport(Model model) {
        var reportId = reportService.createReport();
        reportService.startReportGeneration(reportId);
        model.addAttribute("reportId", reportId);
        return "reportViewer";
    }

    @PostMapping("/getContent")
    public String getReportContent(String id, Model model) throws JsonProcessingException {
        var report = reportService.findReportById(Long.valueOf(id));
        model.addAttribute("reportId", id);
        model.addAttribute("report", report);
        if(Objects.equals(report.getStatus(), String.valueOf(Status.COMPLETED))) {
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            var content = mapper.readValue(report.getContent(), ReportContent.class);
            model.addAttribute("applications", content.getApplications());
            model.addAttribute("appListTime", content.getAppListTime());
            model.addAttribute("numberOfUsers", content.getNumberOfUsers());
            model.addAttribute("userCountTime", content.getUserCountTime());
            model.addAttribute("generalTime", content.getGeneralTime());
        }
        return "reportViewer";
    }
}
