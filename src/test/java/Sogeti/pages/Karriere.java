package Sogeti.pages;

import Sogeti.utilities.Driver;
import Sogeti.utilities.ReusableMethods;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class Karriere {

    private WebDriver driver = Driver.getDriver();
    private static final Logger log = LogManager.getLogger(Karriere.class);

    public Karriere() {
        PageFactory.initElements(driver, this);
    }

    // Header Elemente
    private By offeneStellen = By.xpath("//a[@aria-label='Offene Stellen Menü']/span/span");
    private By unsereFachbereiche = By.xpath("//a[@aria-label='Unsere Fachbereiche Menü']");
    private By karrierewege = By.xpath("//a[@aria-label='Karrierewege Menü']");
    private By guteGrundeFurSogeti = By.xpath("//a[@aria-label='Gute Gründe für Sogeti']");
    private By diversitaetUndInklusion = By.xpath("//a[@aria-label='Diversität und Inklusion']");
    private By bewerbungstippsFAQ = By.xpath("//a[@aria-label='Bewerbungstipps & FAQ']");

    // Methods

    public void clickCareerMenu(String menuName) {
        By locator = getMenuLocator(menuName);
        try {
            ReusableMethods.visibilityOfElement(locator);
            ReusableMethods.clickElementByJS(locator);
            log.info("Erfolgreich auf '{}' geklickt.", menuName);
            ReusableMethods.waitForSeconds(2);
        } catch (Exception e) {
            log.error("Fehler beim Klick auf '{}'.", menuName, e);
            throw e;
        }
    }

    private By getMenuLocator(String menuName) {
        return switch (menuName) {
            case "Offene Stellen" -> offeneStellen;
            case "Unsere Fachbereiche" -> unsereFachbereiche;
            case "Karrierewege" -> karrierewege;
            case "Gute Gründe für Sogeti" -> guteGrundeFurSogeti;
            case "Diversität und Inklusion" -> diversitaetUndInklusion;
            case "Bewerbungstipps & FAQ" -> bewerbungstippsFAQ;
            default -> throw new NoSuchElementException("Ungültige Menübezeichnung: " + menuName);
        };
    }
}