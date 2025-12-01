package Sogeti.pages;


import Sogeti.utilities.Driver;
import Sogeti.utilities.ReusableMethods;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;


public class HomePage {
    WebDriver driver = Driver.getDriver();

    public HomePage() {
        PageFactory.initElements(driver, this);
    }

    private By services = By.xpath("//a[@aria-label='Services Menü']/span/span");
    private By academy = By.xpath("//a[@aria-label='Academy Menü']/span/span");
    private By einblicke = By.xpath("//a[@aria-label='Einblicke Menü']/span/span");
    private By karriere = By.xpath("//div[1]/div[1]/div[1]/nav[1]/ul[1]/li[4]/a[1]/span[1]/span[1]");
    private By uberUns = By.xpath("//a[@aria-label='Über uns Menü']/span/span");


    public boolean isServicesDisplayed(){
       return ReusableMethods.isDisplayed(services);
    }
    public boolean isAcademyDisplayed(){
        return ReusableMethods.isDisplayed(academy);
    }
    public boolean isEinblickeDisplayed(){
        return ReusableMethods.isDisplayed(einblicke);
    }
    public boolean isKarriereDisplayed(){
        return ReusableMethods.isDisplayed(karriere);
    }
    public boolean isUberUnsDisplayed(){
        return ReusableMethods.isDisplayed(uberUns);
    }

    public void moveToHeader(String headerName){
        Actions action = new Actions(driver);
        ReusableMethods.waitForSeconds(1);
        WebElement targetElement;

        switch (headerName){

            case "Services":
                targetElement = driver.findElement(services);
                break;
            case "Academy":
                targetElement = driver.findElement(academy);
                break;
            case "Einblicke":
                targetElement = driver.findElement(einblicke);
                break;
            case "Karriere":
                targetElement = driver.findElement(karriere);
                break;
            case "Über uns":
                targetElement = driver.findElement(uberUns);
                break;
            default:
                throw new NoSuchElementException("Fehler!: Ungültige Menübezeichnung: " + headerName);
        }
        action.moveToElement(targetElement).perform();
        ReusableMethods.waitForSeconds(3);
    }

        }


