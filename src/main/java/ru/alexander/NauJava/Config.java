package ru.alexander.NauJava;

import Core.LogEvent;
import DataAccess.EventRepository;
import Service.LogEventServiceImpl;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class Config {
    @Value("${app.name}")
    private String appName;

    @Value("${app.version}")
    private String appVersion;

    @PostConstruct
    public void init() {
        System.out.printf("Application: %s, Version: %s\n", appName, appVersion);
    }

    @Bean
    @Scope(value = BeanDefinition.SCOPE_SINGLETON)
    public Map<Long, LogEvent> logEvents() {
        return new HashMap<Long, LogEvent>();
    }

    @Bean
    @Scope(value = BeanDefinition.SCOPE_SINGLETON)
    public EventRepository eventRepository() {
        return new EventRepository(logEvents());
    }

    @Bean
    @Scope(value = BeanDefinition.SCOPE_SINGLETON)
    public LogEventServiceImpl logEventService() {
        return new LogEventServiceImpl(eventRepository());
    }

    @Bean
    @Scope(value = BeanDefinition.SCOPE_SINGLETON)
    public CommandProcessor commandProcessor() {
        return new CommandProcessor(logEventService());
    }
}
