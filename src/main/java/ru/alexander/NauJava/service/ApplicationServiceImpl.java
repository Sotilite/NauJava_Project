package ru.alexander.NauJava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.alexander.NauJava.entity.Application;
import ru.alexander.NauJava.repository.ApplicationRepository;

import java.util.List;

@Service
public class ApplicationServiceImpl implements ApplicationService {
    @Autowired
    ApplicationRepository applicationRepository;

    @Override
    public List<Application> allApplications() {
        return (List<Application>) applicationRepository.findAll();
    }
}
