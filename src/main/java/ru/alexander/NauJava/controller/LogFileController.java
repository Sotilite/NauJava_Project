package ru.alexander.NauJava.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.alexander.NauJava.entity.LogFile;
import ru.alexander.NauJava.repository.LogFileRepository;

@Controller
@RequestMapping("/logFiles")
public class LogFileController {
    @Autowired
    private LogFileRepository logFileRepository;

    @GetMapping("/findByName")
    public LogFile findByName(@RequestParam String name) {
        return logFileRepository.findByName(name);
    }
}
