package steps;

import com.codeborne.selenide.WebDriverRunner;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.ru.Дано;
import io.cucumber.java.ru.Тогда;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.close;
import static com.google.common.primitives.Ints.max;
import static java.lang.Thread.sleep;

public class case1 {

    public static ChromeDriver driver;
    Random rnd = new Random();

    @Before
    public static void setUp() {
        System.setProperty("webdriver.chrome.driver", "C://Chromedriver//chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @After
    public static void tearDown() {
        driver.quit();
        close();
    }

    @Дано("Вход в бсл через (.*) с паролем (.*)")
    public void входвбслчерез_uatuserс_паролем(String login, String passwd) throws InterruptedException{
        driver.get("https://sso.kz00c1.kz.infra/opensso/UI/Login?goto=https%3A%2F%2Fbsl.kz00c1.kz.infra%2Fbsl%2Fhome%3F");
        driver.findElementById("IDToken1").sendKeys(login);
        sleep(1500);
        driver.findElementById("IDToken2").sendKeys(passwd);
        sleep(1500);
        driver.findElementById("kc-login").click();
        sleep(1500);
        //driver.findElementById("kc-login")
    }

    @Тогда("Нажать на кнопку «Создать банк»")
    public void нажатьнакнопкуСоздатьбанк() throws InterruptedException{
        driver.findElementByXPath("/html/body/div[2]/div[1]/div[1]/div[1]/div[1]/div[3]/div[1]").click(); // click create bank
        sleep(3000);
    }

    @Дано("Данные банка")
    public void данные_банка() throws InterruptedException {
        String bankName = "Bank" + (rnd.nextInt(999)+1000);
        driver.findElementById("id24").sendKeys(bankName); // bank code
        sleep(1500);
        driver.findElementById("id25").sendKeys("143"); // 2 bank code
        sleep(1500);
        driver.findElementByXPath("/html/body/div[2]/div[1]/div/form/div[2]/div[1]/div/ul[1]/li/input").sendKeys("BankOfAmerica"); // bank name
        sleep(2500);

        WebElement dropdownBrands = driver.findElementByXPath("/html/body/div[2]/div[1]/div/form/div[2]/div[1]/div/ul[2]/li[1]/select");
        Select s = new Select(dropdownBrands);
        s.selectByValue("ACTIVE"); //status

        sleep(1500);
        driver.findElementById("id29").sendKeys("12"); // sdvig date
        sleep(1500);
        driver.findElementById("id15").click(); // OK
        sleep(3000);
    }

    @Тогда("Проверка страницы банка")
    public void проверкастраницыклиента() throws InterruptedException{
        String name = driver.findElementByXPath("/html/body/div[2]/div[1]/div[1]/div/div[1]/ul[1]/li[1]/span[2]/span").getText();
        Assert.assertNotNull(name, "not null");
    }
}
