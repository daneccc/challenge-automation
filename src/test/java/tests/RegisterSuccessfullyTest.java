package tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class RegisterSuccessfullyTest {

    private WebDriver browser;

    @Before
    public void setUp() {
        // opening the browser
        System.setProperty("webdriver.chrome.driver", "chromedriver");
        browser = new ChromeDriver();
        browser.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

        // STEP 01: access the URL
        browser.get("https://www.organizze.com.br/");
    }

    @Test
    public void testCase() {
        // STEP 02: click on "Cadastre-se"
        browser.findElement(By.linkText("Cadastre-se")).click();

        // STEP 03: select "Organizar as minhas finanças" option. I can't find
        //browser.findElement(By.linkText("Organizar as minhas finanças")).click();

        // Identifying the registration form
        WebElement signUpForm = browser.findElement(By.className("signup"));

        // STEP 04: fill in E-mail on "Seu email" field
        signUpForm.findElement(By.id("email")).sendKeys("29@email.com");

        // STEP 05: fill in Password on "Senha" field
        signUpForm.findElement(By.id("password")).sendKeys("testaut2020");

        // STEP 06: fill in Password on "Repetir Senha" field
        signUpForm.findElement(By.id("passwordConfirmation")).sendKeys("testaut2020");

        // STEP 07: check the "Li e concordo com os termos de uso" option
        signUpForm.findElement(By.id("termsOfUse")).click();

        // STEP 08: click on "Começar a usar"
        signUpForm.findElement(By.cssSelector("button.btn.btn-primary.btn-rounded")).click();

        // Expected outcome:
        WebDriverWait waitLoadingBox = new WebDriverWait(browser, 15);
        waitLoadingBox.until(ExpectedConditions.visibilityOfElementLocated(By.className("signup__loading-box")));
        String messageToWait = browser.findElement(By.className("signup__status-title")).getText();
        assertEquals("Aguarde, estamos preparando sua conta...", messageToWait);

        WebDriverWait waitSuccessBox = new WebDriverWait(browser, 15);
        waitSuccessBox.until(ExpectedConditions.visibilityOfElementLocated(By.className("signup__success-box")));
        String congratsMessage = browser.findElement(By.xpath("//html/body/div[2]/div/div[2]/h3")).getText();
        String infoMessage = browser.findElement(By.xpath("/html/body/div[2]/div/div[2]/p")).getText();
        assertEquals("Parabéns! O Organizze já está preparado para você!", congratsMessage);
        assertEquals("Enviamos um e-mail para sua caixa de entrada. Confirme seu cadastro para receber um e-mail importante da nossa equipe.", infoMessage);

        browser.findElement(By.cssSelector("button.btn.btn-primary")).isDisplayed();
    }

    @After
    public void tearDown() {
        // Close the browser
        browser.quit();
    }
}
