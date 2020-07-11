package tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class RegisterSuccessfullyTest {

    private WebDriver browser;

    @Before
    public void setUp() {
        //opening the browser
        System.setProperty("webdriver.chrome.driver", "chromedriver");
        browser = new ChromeDriver();
        browser.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);

        //navigating to the page
        browser.get("https://www.organizze.com.br/");
    }

    @Test
    public void testCase() {
        //clicar no link que possui o texto "Cadastre-se"
        WebElement clickRegister = browser.findElement(By.linkText("Cadastre-se"));
        clickRegister.click();

        //identificando o formulário de cadastro
        WebElement signUpForm = browser.findElement(By.className("signup"));

        //digitar no campo com nome "email" que está dentro do "signup__box" o texto "teste@email.com"
        signUpForm.findElement(By.id("email")).sendKeys("test20@email.com");

        //digitar no campo "password" que está dentro do "signup__box" o texto "testaut2020"
        signUpForm.findElement(By.id("password")).sendKeys("testaut2020");

        //digitar no campo "passwordConfirmation" que está dentro do "signup__box" o texto "testaut2020"
        signUpForm.findElement(By.id("passwordConfirmation")).sendKeys("testaut2020");

        //clicar/selecionar no campo "termsOfUse"
        signUpForm.findElement(By.id("termsOfUse")).click();

        //clicar no botao "Começar a usar"
        signUpForm.findElement(By.cssSelector("button.btn.btn-primary.btn-rounded")).click();

        //singup__second
        WebElement signUpSuccess = browser.findElement(By.cssSelector("button.btn.btn-primary"));
        signUpSuccess.click();
    }

    @After
    public void tearDown() {
        //fechar o navegador
        browser.quit();
    }
}
