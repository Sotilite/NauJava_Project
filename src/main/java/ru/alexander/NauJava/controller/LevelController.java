package ru.alexander.NauJava.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.alexander.NauJava.entity.Level;
import ru.alexander.NauJava.repository.LevelRepository;

@RestController
@RequestMapping("/levels")
public class LevelController {
    @Autowired
    private LevelRepository levelRepository;

    @GetMapping("/findByTitle")
    public Level findByTitle(@RequestParam String title) {
        return levelRepository.findByTitle(title);
    }
}
