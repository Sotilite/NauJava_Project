package ru.alexander.NauJava.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.alexander.NauJava.repository.LogEventRepository;

@Controller
@RequestMapping("/logEvents")
public class LogEventView {
    @Autowired
    private LogEventRepository eventRepository;

    @GetMapping("/list")
    public String appListView(Model model) {
        var events = eventRepository.findAll();
        model.addAttribute("events", events);
        return "eventList";
    }
}
