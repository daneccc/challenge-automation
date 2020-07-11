package br.com.organizze.tests;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class RegisterSuccessfullyTest {

    private WebDriver browser;
    private String url = "https://www.organizze.com.br/";
    private String password = "testaut2020";

    // used to generate new random emails in Step 04
    protected String getRandomString() {
        String chars = "abcdefghijklmnopqrstuvwxyz1234567890";
        StringBuilder user = new StringBuilder();
        Random rnd = new Random();
        while (user.length() < 10) { // length of the random string
            int index = (int) (rnd.nextFloat() * chars.length());
            user.append(chars.charAt(index));
        }
        return user.toString();
    }

    @Before
    public void setUp() {
        // opening the browser
        System.setProperty("webdriver.chrome.driver", "chromedriver");
        browser = new ChromeDriver();
        browser.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        // STEP 01: access the URL
        browser.get(url);
    }

    @Test
    public void testCase() {
        // STEP 02: click on "Cadastre-se"
        browser.findElement(By.linkText("Cadastre-se")).click();

        // STEP 03: select "Organizar as minhas finanças" option. I can't find it on the website
        //browser.findElement(By.linkText("Organizar as minhas finanças")).click();

        // Identifying the registration form
        WebElement signUpForm = browser.findElement(By.className("signup"));

        // STEP 04: fill in E-mail on "Seu email" field
        signUpForm.findElement(By.id("email")).sendKeys(getRandomString() + "@email.com");

        // STEP 05: fill in Password on "Senha" field
        signUpForm.findElement(By.id("password")).sendKeys(password);

        // STEP 06: fill in Password on "Repetir Senha" field
        signUpForm.findElement(By.id("passwordConfirmation")).sendKeys(password);

        // STEP 07: check the "Li e concordo com os termos de uso" option
        signUpForm.findElement(By.id("termsOfUse")).click();

        // STEP 08: click on "Começar a usar"
        signUpForm.findElement(By.cssSelector("button.btn.btn-primary.btn-rounded")).click();

        // Loading box
        WebDriverWait waitLoadingBox = new WebDriverWait(browser, 20);
        waitLoadingBox.until(ExpectedConditions.visibilityOfElementLocated(By.className("signup__loading-box")));

        // Expected outcome:
        WebDriverWait waitSuccessBox = new WebDriverWait(browser, 20);
        waitSuccessBox.until(ExpectedConditions.visibilityOfElementLocated(By.className("signup__success-box")));
        String congratsMessage = browser.findElement(By.xpath("//html/body/div[2]/div/div[2]/h3")).getText();
        String infoMessage = browser.findElement(By.xpath("/html/body/div[2]/div/div[2]/p")).getText();
        assertEquals("Parabéns! O Organizze já está preparado para você!", congratsMessage);
        assertEquals("Enviamos um e-mail para sua caixa de entrada. Confirme seu cadastro para receber um e-mail importante da nossa equipe.", infoMessage);

        String btnStartNow = browser.findElement(By.xpath("/html/body/div[2]/div/div[2]/a")).getText();
        assertEquals("Ok, começar agora", btnStartNow);
        browser.findElement(By.cssSelector("button.btn.btn-primary")).isDisplayed();
    }

    @After
    public void tearDown() {
        // Close the browser
        browser.quit();
    }
}
