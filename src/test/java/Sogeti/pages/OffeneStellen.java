package Sogeti.pages;

import Sogeti.utilities.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.List;

public class OffeneStellen {

    private WebDriver driver = Driver.getDriver();
    private static final Logger log = LogManager.getLogger(OffeneStellen.class);

    public OffeneStellen() {
        PageFactory.initElements(driver, this);
    }

    // Elemente
    private By suchFeld = By.id("searchsubmit");
    private By searchButton = By.xpath("//button[@class='search-button']");
    private By mehrLaden = By.xpath("//a[@class='filters-more']");
    private By standort = By.xpath("//div[contains(@class,'react-select__placeholder') and text()='Standort']");
    private By frankfurt = By.xpath("//div[contains(@class,'react-select__option') and contains(text(),'Frankfurt')]");
    private By vertragsart = By.xpath("//div[contains(@class,'react-select__placeholder') and text()='Vertragsart']");
    private By fullTime = By.xpath("//div[contains(@class,'react-select__option') and contains(text(),'Full-time')]");
    private By gefundeneJobs = By.xpath("//a[@class='table-tr filter-box tag-active joblink']");
    private By job = By.xpath("//a[@class='cta-link']");

    //Methods

    public void searchJob(String jobName) {
        try {
            ReusableMethods.waitForElementToBeClickable(driver, suchFeld, 10);
            ReusableMethods.sendKeys(suchFeld, jobName);
            ReusableMethods.clickElement(searchButton);
            log.info("Job-Suche mit Begriff '{}' durchgeführt", jobName);
        } catch (Exception e) {
            log.error("Fehler bei Job-Suche mit '{}' ", jobName, e);
            throw e;
        }
    }

    public void selectLocation() {
        try {
            Actions actions = new Actions(driver);
            actions.clickAndHold(driver.findElement(standort)).perform();
            ReusableMethods.waitForElementToBeClickable(driver, frankfurt, 10);
            ReusableMethods.clickElementByJS(frankfurt);
            ReusableMethods.waitForSeconds(4);
            log.info("Standort 'Frankfurt' ausgewählt");
        } catch (Exception e) {
            log.error("Fehler beim Auswählen des Standorts", e);
            throw e;
        }
    }

    public void selectContractType() {
        try {
            Actions actions = new Actions(driver);
            actions.clickAndHold(driver.findElement(vertragsart)).perform();
            ReusableMethods.waitForElementToBeClickable(driver, fullTime, 10);
            ReusableMethods.clickElementByJS(fullTime);
            log.info("Vertragsart 'Full-time' ausgewählt");
        } catch (Exception e) {
            log.error("Fehler beim Auswählen der Vertragsart", e);
            throw e;
        }
    }

        public void selectJob(String keyword) {
            ReusableMethods.visibilityOfElements(gefundeneJobs);

            List<WebElement> jobList = driver.findElements(gefundeneJobs);

            for (WebElement element : jobList) {
                if (element.getText().contains(keyword)) {
                    element.click();
                    log.info("Job mit Begriff '{}' ausgewählt", keyword);
                    return;
                }
            }
            Assert.fail("Kein Jobtitel mit dem Begriff '" + keyword + "' gefunden");
        }


    public void clickApplyNow() {
        try {
            ReusableMethods.clickElement(job);
            log.info("Auf 'Jetzt bewerben' geklickt.");
        } catch (Exception e) {
            log.error("Fehler beim Klick auf 'Jetzt bewerben'", e);
            throw e;
        }
    }

    public void scrollAndLoadMore() {
        try {
            JavascriptUtils.seiteLangsamNachUntenScrollen(driver, 20);
            ReusableMethods.waitForElementToBeClickable(driver, mehrLaden, 10);
            ReusableMethods.clickElement(mehrLaden);
            JavascriptUtils.seiteLangsamNachUntenScrollen(driver, 5);
            JavascriptUtils.scrollToVisibleElement(driver, suchFeld);
            ReusableMethods.waitForSeconds(4);
            log.info("Seite gescrollt und 'Mehr laden' geklickt");
        } catch (Exception e) {
            log.error("Fehler beim Scrollen und Laden weiterer Jobs", e);
            throw e;
        }
    }
}