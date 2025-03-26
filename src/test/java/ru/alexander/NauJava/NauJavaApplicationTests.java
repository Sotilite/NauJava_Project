package ru.alexander.NauJava;

import customrepository.ApplicationRepositoryCustom;
import customrepository.LogEventRepositoryCustom;
import entity.*;
import levelservice.LevelService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import repository.*;

import java.time.LocalDateTime;

@SpringBootTest
class NauJavaApplicationTests {
	@Autowired
	UserRepository userRepository;
	RoleRepository roleRepository;
	ApplicationRepository applicationRepository;
	ApplicationRepositoryCustom applicationRepositoryCustom;
	LogFileRepository logFileRepository;
	LogEventRepository logEventRepository;
	LogEventRepositoryCustom logEventRepositoryCustom;
	LevelRepository levelRepository;
	LevelService levelService;

    @Test
	void contextLoads() {}

	/**
	 * Загрузка данных в БД
	 */
	@Test
	void loadData() {
		var viewer = new Role("VIEWER", "User can only view logs");
		roleRepository.save(viewer);

		var creationDate = LocalDateTime.of(2020, 5, 23, 14, 23);
		var sasha = new User("Sasha", "Alex3000", "qwerty123456", viewer, creationDate);
		userRepository.save(sasha);

		var yandex = new Application(sasha, "yandex", "C:/Users/Admin/yandex.exe", "1.2.3");
		applicationRepository.save(yandex);

		var logFile = new LogFile(yandex, "yandex_38294", LocalDateTime.now(), 42.3);
		logFileRepository.save(logFile);

		var info = new Level("INFO", "It just describes what's going on");
		var warning = new Level("WARNING", "Warns about possible problems");
		var error = new Level("ERROR", "Indicates an error that occurred");
		levelRepository.save(info);
		levelRepository.save(warning);
		levelRepository.save(error);

		logEventRepository.save(new LogEvent(logFile, info,
				LocalDateTime.of(2025, 2, 3, 23, 3), "Reading data"));
		logEventRepository.save(new LogEvent(logFile, warning,
				LocalDateTime.of(2025, 3, 19, 3, 45), "Restart process"));
		logEventRepository.save(new LogEvent(logFile, info,
				LocalDateTime.of(2025, 3, 21, 9, 32), "Recording data"));
		logEventRepository.save(new LogEvent(logFile, error,
				LocalDateTime.of(2025, 3, 23, 17, 15), "File does not exist"));
		logEventRepository.save(new LogEvent(logFile, warning,
				LocalDateTime.of(2025, 3, 26, 18, 29), "Incorrect variable"));
	}

	/**
	 * Тестирование метода по нахождению
	 * логов в указанном диапазоне (пятый раздел)
	 */
	@Test
	void findEventsByTimestampBetween() {
		var startTime = LocalDateTime.of(2025, 3, 20, 7, 54);
		var endTime = LocalDateTime.of(2025, 3, 25, 23, 59);
		var events = logEventRepository.findLogEventsByTimestampBetween(startTime, endTime);
        Assertions.assertEquals(3, events.size());
	}

	/**
	 * Тестирование метода по нахождению
	 * загруженных программ у пользователя (пятый раздел)
	 */
	@Test
	void findApplicationsByUser() {
		var user = userRepository.findByLogin("Alex3000");
		var apps = applicationRepository.findByUser(user);
		Assertions.assertEquals(1, apps.size());
	}

	/**
	 * Тестирование метода по нахождению логов
	 * в указанном диапазоне (шестой раздел) c помощью Criteria
	 */
	@Test
	void findEventsByTimestampBetweenWithCriteria() {
		var startTime = LocalDateTime.of(2025, 3, 20, 7, 54);
		var endTime = LocalDateTime.of(2025, 3, 25, 23, 59);
		var events = logEventRepositoryCustom.findLogEventsByTimestampBetween(startTime, endTime);
		Assertions.assertEquals(3, events.size());
	}

	/**
	 * Тестирование метода по нахождению загруженных
	 * программ у пользователя (шестой раздел) с помощью Criteria
	 */
	@Test
	void findApplicationsByUserWithCriteria() {
		var user = userRepository.findByLogin("Alex3000");
		var apps = applicationRepositoryCustom.findByUser(user);
		Assertions.assertEquals(1, apps.size());
	}

	/**
	 * Тестирование метода по удалению
	 * уровня из репозитория (седьмой раздел)
	 */
	@Test
	void deleteLevel() {
		//Положительный кейс
		var error = levelRepository.findByName("ERROR");
		levelService.deleteLevel(error.getName());
		var levelCount = levelRepository.count();
		Assertions.assertEquals(2, levelCount);

		//Отрицательный кейс
		var logFile =logFileRepository.findByName("yandex_38294");
		var debug = new Level("DEBUG", "Talk about the details during debugging");
		logEventRepository.save(new LogEvent(logFile, debug, LocalDateTime.now(), "Debugging process"));
		levelService.deleteLevel(debug.getName());
		Assertions.assertEquals(2, levelCount);
	}
}
