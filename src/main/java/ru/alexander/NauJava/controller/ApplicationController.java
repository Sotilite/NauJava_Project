package ru.alexander.NauJava.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.alexander.NauJava.entity.Application;
import ru.alexander.NauJava.repository.ApplicationRepository;

import java.util.List;

@RestController
@RequestMapping("/applications")
public class ApplicationController {
    @Autowired
    private ApplicationRepository appRepository;

    @GetMapping("/findAllByUserLogin")
    public List<Application> findAllByUserLogin(@RequestParam String login) {
        return appRepository.findAllByUserLogin(login);
    }

    @GetMapping("/findByName")
    public Application findByName(@RequestParam String appName) {
        return appRepository.findByName(appName);
    }
}
