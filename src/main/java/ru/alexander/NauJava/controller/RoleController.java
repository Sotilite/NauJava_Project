package ru.alexander.NauJava.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.alexander.NauJava.entity.Role;
import ru.alexander.NauJava.repository.RoleRepository;

@Controller
@RequestMapping("/roles")
public class RoleController {
    @Autowired
    private RoleRepository roleRepository;

    @GetMapping("/findByTitle")
    public Role findByTitle(@RequestParam String title) {
        return roleRepository.findByTitle(title);
    }
}
