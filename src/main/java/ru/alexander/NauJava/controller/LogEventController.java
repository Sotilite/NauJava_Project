package ru.alexander.NauJava.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.alexander.NauJava.entity.LogEvent;
import ru.alexander.NauJava.repository.LogEventRepository;

import java.util.List;

@Controller
@RequestMapping("/logEvents")
public class LogEventController {
    @Autowired
    private LogEventRepository logEventRepository;

    @GetMapping("/findAllByLevelTittle")
    public List<LogEvent> findAllByLevelTittle(@RequestParam String title) {
        return logEventRepository.findAllByLevelTittle(title);
    }
}
