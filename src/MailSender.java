import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

/**
 *
 * @author kurbatovbn
 */
class MailRuMainPage {
    //реализация POM для страницы с формой логирования
    private WebDriver driver;
    private By userName = By.id("mailbox:login");
    private By password = By.id("mailbox:password");
    private By login = By.id("mailbox:submit");
    public MailRuMainPage(WebDriver driver) {
        this.driver = driver;
    }
    
    //ввод логина:
    public void setUserName(String strUserName){
        driver.findElement(userName).sendKeys(strUserName);
    }
    
    //ввод пароля:
    public void setPassword(String strPassword) {
        driver.findElement(password).sendKeys(strPassword);
    }
    
    //клик на кнопку войти:
    public void clickLogin(){
        driver.findElement(login).click();
    }
}

class MailBoxPage {
    //реализация РОМ для страницы с почтой
    private WebDriver driver;
    private By writeMail = By.id("b-toolbar__btn");
    public MailBoxPage(WebDriver driver) {
        this.driver = driver;
    }
    //клик на кнопку Написать письмо
    void clickWrite() {
        driver.findElement(writeMail).click();
    }
}

class MailWritePage {
    //реализация POM для страницы написания письма
    private WebDriver driver;
    private By address = By.className("js-input compose__labels__input");
    private By letterArea = By.id("tinymce");
    private By sendButton = By.className("b-toolbar__btn__text");
    
    public MailWritePage(WebDriver driver) {
        this.driver = driver;
    }
    
    //ввести адресс
    public void enterTheAdress(String email) {
        driver.findElement(address).sendKeys(email);
    }
    
    //написать письмо
    public void writeLetter(String letter) {
        driver.findElement(letterArea).sendKeys(letter);
    }
    
    public void sendLetter() {
        driver.findElement(sendButton).click();
    }
}


public class MailSender {
    static String testEmailLogin = "mailForMailSender";
    static String testEmailPassword = "password_for_test";
    static String mailRuUrl = "mail.ru";
    
    static WebDriver driver;
    
    public static void main(String[] args)  {
        System.setProperty("webdriver.gecko.driver", "src/geckodriver");
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(mailRuUrl);

        MailRuMainPage mainPage = new MailRuMainPage(driver);
        mainPage.setUserName(testEmailLogin);
        mainPage.setPassword(testEmailPassword);
        mainPage.clickLogin();

        MailBoxPage mailPage = new MailBoxPage(driver);
        mailPage.clickWrite();

        MailWritePage writePage = new MailWritePage(driver);
        writePage.enterTheAdress(testEmailLogin + "@mail.ru");
        writePage.writeLetter("ololo\nololo");
        writePage.sendLetter();

        //закрываем браузер
        driver.quit();
    } 
}
