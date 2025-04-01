package ru.alexander.NauJava.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.alexander.NauJava.repository.ApplicationRepository;

@Controller
@RequestMapping("/applications")
public class ApplicationView {
    @Autowired
    private ApplicationRepository appRepository;

    @GetMapping("/list")
    public String appListView(Model model) {
        var apps = appRepository.findAll();
        model.addAttribute("applications", apps);
        return "applicationList";
    }
}
