package ru.alexander.NauJava.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.alexander.NauJava.service.ReportService;

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
    public String getReportContent(String id, Model model) {
        var report = reportService.findReportById(Long.valueOf(id));
        model.addAttribute("reportId", id);
        model.addAttribute("report", report);
        return "reportViewer";
    }
}
