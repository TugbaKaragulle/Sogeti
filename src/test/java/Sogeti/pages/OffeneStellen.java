package Sogeti.pages;

import Sogeti.utilities.*;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class OffeneStellen {

   WebDriver driver =  Driver.getDriver();

    public OffeneStellen(){
        PageFactory.initElements(driver,this);
    }

    private By suchFeld = By.id("searchsubmit");
    private By mehrLaden = By.xpath("//a[@class='filters-more']");
    private By searchButton = By.xpath("//button[@class='search-button']");
    private By standort = By.xpath("//div[contains(@class,'react-select__placeholder') and text()='Standort']");
    private By frankfurt = By.xpath("//div[contains(@class,'react-select__option') and contains(text(),'Frankfurt')]");
    private By vertragsart = By.xpath("//div[contains(@class,'react-select__placeholder') and text()='Vertragsart']");
    private By fullTime = By.xpath("//div[contains(@class,'react-select__option') and contains(text(),'Full-time')]");
    private By gefundeneJobs = By.xpath("//a[@class='table-tr filter-box tag-active joblink']"); //List
    private By job = By.xpath("//a[@class='cta-link']");


    public void klickJetztBewerben(){
        ReusableMethods.clickElement(job);
        ReusableMethods.waitForSeconds(3);
    }

    public void waehleJob(String wort){
        boolean gefunden = false;
        try {
            // 1. İlan listesinin yüklenmesini bekle (Timeout'u önler)
            ReusableMethods.visibilityOfElements(gefundeneJobs);

            // 2. Listeyi al
            List<WebElement> jobList = driver.findElements(gefundeneJobs);

            // 3. Döngüyü başlat
            for (WebElement element : jobList ){
                if (element.getText().contains(wort)){
                    element.click();
                    gefunden = true;
                    return;
                }
            }
            if (!gefunden) {
                throw new NoSuchElementException("Fehler: Kein Jobtitel mit dem Begriff '" + wort + "' gefunden.");
            }

        } catch (TimeoutException e) {
            throw new NoSuchElementException("Fehler: Die Jobliste konnte innerhalb der Wartezeit nicht geladen werden.");
        }
    }


    public void waehleStandort(){

        Actions actions = new Actions(driver);
        actions.clickAndHold(driver.findElement(standort)).perform();
        ReusableMethods.waitForElementToBeClickable(driver,frankfurt,10);
        ReusableMethods.clickElementByJS(frankfurt);
        ReusableMethods.waitForSeconds(3);
    }

    public void waehleVertragsart(){

        Actions actions = new Actions(driver);
        actions.clickAndHold(driver.findElement(vertragsart)).perform();
        ReusableMethods.waitForElementToBeClickable(driver,fullTime,10);
        ReusableMethods.clickElementByJS(fullTime);
        ReusableMethods.waitForSeconds(3);
    }


    public void jobSuche(String job){
        ReusableMethods.waitForElementToBeClickable(driver,suchFeld,10);
        ReusableMethods.sendKeys(suchFeld,job);
        ReusableMethods.clickElement(searchButton);
        ReusableMethods.waitForSeconds(2);
    }

    public void scrollToElement(){

        JavascriptUtils.seiteLangsamNachUntenScrollen(driver,20);
        ReusableMethods.waitForElementToBeClickable(driver,mehrLaden,10);
        ReusableMethods.waitForSeconds(2);
        ReusableMethods.clickElement(mehrLaden);
        ReusableMethods.waitForSeconds(2);
        JavascriptUtils.seiteLangsamNachUntenScrollen(driver,5);
        ReusableMethods.waitForSeconds(2);
        JavascriptUtils.scrollToVisibleElement(driver, driver.findElement(suchFeld));
        ReusableMethods.waitForSeconds(2);
    }

}
