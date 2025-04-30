package ru.alexander.NauJava;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.alexander.NauJava.entity.*;
import ru.alexander.NauJava.repository.*;

import java.time.LocalDateTime;
import java.util.Arrays;

@SpringBootTest
class NauJavaApplicationTests {
	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	ApplicationRepository applicationRepository;

	@Autowired
	LogFileRepository logFileRepository;

	@Autowired
	LogEventRepository logEventRepository;

	@Autowired
	LevelRepository levelRepository;

	/**
	 * Добавляет тестовые данные в БД
	 * */
	@Test
	void addTestDataToDB() {
		//Добавление роли
		var user = new Role("USER", "User can only view logs");
		var admin = new Role("ADMIN", "User can add new users");
		roleRepository.saveAll(Arrays.asList(user, admin));

		//Добавление пользователя
		var creationDate = LocalDateTime.of(2020, 5, 23, 14, 23);
		var sasha = new User("Sasha", "Alex3000", "qwerty123456", user, creationDate);
		userRepository.save(sasha);

		//Добавление программы
		var yandex = new Application(sasha, "yandex", "C:/Users/Admin/yandex.exe", "1.2.3");
		var windows = new Application(sasha, "windows", "C:/Users/Admin/system32/windows.exe", "3.4.1");
		applicationRepository.save(yandex);
		applicationRepository.save(windows);

		//Добавление файла с логами программы
		var logFile = new LogFile(yandex, "yandex_38294", LocalDateTime.now(), 42.3);
		logFileRepository.save(logFile);

		//Добавление уровней логов
		levelRepository.save(new Level("INFO", "It just describes what's going on"));
		levelRepository.save(new Level("WARNING", "Warns about possible problems"));
		levelRepository.save(new Level("ERROR", "Indicates an error that occurred"));
		var info = levelRepository.findByTitle("INFO");
		var warning = levelRepository.findByTitle("WARNING");
		var error = levelRepository.findByTitle("ERROR");

		//Добавление логов
		logEventRepository.save(new LogEvent(logFile, info,
				LocalDateTime.of(2025, 2, 3, 23, 3), "Reading data"));
		logEventRepository.save(new LogEvent(logFile, warning,
				LocalDateTime.of(2025, 3, 19, 3, 45), "Restart process"));
		logEventRepository.save(new LogEvent(logFile, error,
				LocalDateTime.of(2025, 3, 23, 17, 15), "File does not exist"));
	}

	/**
	 * Удаляет тестовые данные из БД
	 * */
	@Test
	void deleteTestDataFromDB() {
		logEventRepository.deleteAll();
		levelRepository.deleteAll();
		logFileRepository.deleteAll();
		applicationRepository.deleteAll();
		userRepository.deleteAll();
		roleRepository.deleteAll();
	}
}
