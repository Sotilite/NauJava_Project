package ru.alexander.NauJava;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class SeleniumTest {
    private WebDriver webDriver;

    @BeforeEach
    public void setUp() {
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
    }

    @Test
    public void testSuccessfulLoginAndLogout() throws InterruptedException {
        webDriver.get("http://localhost:8080/login");

        var username = webDriver.findElement(By.id("username"));
        var password = webDriver.findElement(By.id("password"));
        var loginBtn = webDriver.findElement(By.className("primary"));

        username.sendKeys("sasha");
        password.sendKeys("1");
        loginBtn.click();

        Thread.sleep(1000);
        assertTrue(Objects.requireNonNull(webDriver.getCurrentUrl()).contains("/home"));
        assertTrue(webDriver.findElement(By.className("home")).isDisplayed());

        var logoutLink = webDriver.findElement(By.className("logout"));
        logoutLink.click();
        Thread.sleep(1000);
        var logoutBtn = webDriver.findElement(By.className("primary"));
        logoutBtn.click();

        Thread.sleep(1000);
        assertTrue(webDriver.getCurrentUrl().contains("/login"));
        assertTrue(webDriver.findElement(By.className("login-form")).isDisplayed());

        webDriver.quit();
    }
}
