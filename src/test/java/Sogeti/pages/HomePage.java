package Sogeti.pages;

import Sogeti.utilities.Driver;
import Sogeti.utilities.ReusableMethods;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

    private WebDriver driver = Driver.getDriver();
    private static final Logger log = LogManager.getLogger(HomePage.class);

    public HomePage() {
        PageFactory.initElements(driver, this);
    }

    // Header Elemente
    private By services = By.xpath("//a[@aria-label='Services Menü']/span/span");
    private By academy = By.xpath("//a[@aria-label='Academy Menü']/span/span");
    private By einblicke = By.xpath("//a[@aria-label='Einblicke Menü']/span/span");
    //private By karriere = By.xpath("//div[1]/div[1]/div[1]/nav[1]/ul[1]/li[4]/a[1]/span[1]/span[1]");
    //private By karriere = By.xpath("//a[text()='Karriere']");
    private By karriere = By.xpath("//a[@aria-label='Karriere Menü']");
    private By uberUns = By.xpath("//a[@aria-label='Über uns Menü']/span/span");
    private By shadowhost = By.cssSelector("aside#usercentrics-cmp-ui");
    private By cookiesDenyButton = By.cssSelector("button.deny.uc-deny-button");


    // Cookies Entfernung
    public void removeCookies() {
        ReusableMethods.waitForSeconds(4);
        try {
            WebElement shadow = driver.findElement(shadowhost);
            SearchContext shadowRoot = shadow.getShadowRoot();
            WebElement denyButton = shadowRoot.findElement(cookiesDenyButton);
            denyButton.click();
            log.info("Cookies-Banner erfolgreich abgelehnt.");
        } catch (NoSuchElementException e) {
            log.warn("Cookies-Banner nicht gefunden, überspringe Schritt.");
        }
    }

    // Prüft, ob ein Header sichtbar ist.
    public boolean isHeaderDisplayed(String headerName) {
        By locator = getHeaderLocator(headerName);
        boolean displayed = ReusableMethods.isDisplayed(locator);
        log.info("Header '{}' sichtbar: {}", headerName, displayed);
        return displayed;
    }

    // Bewegt die Maus zu einem Header-Element.
    public void hoverOverHeader(String headerName) {
        By locator = getHeaderLocator(headerName);
        WebElement element = driver.findElement(locator);
        new Actions(driver).moveToElement(element).perform();
        log.info("Maus über '{}' bewegt.", headerName);
        ReusableMethods.waitForSeconds(4);
    }

    // Hilfsmethode: Header-Name -> Locator
    private By getHeaderLocator(String headerName) {
        return switch (headerName) {
            case "Services" -> services;
            case "Academy" -> academy;
            case "Einblicke" -> einblicke;
            case "Karriere" -> karriere;
            case "Über uns" -> uberUns;
            default -> throw new NoSuchElementException("Fehler!: Ungültige Menübezeichnung: " + headerName);
        };
    }
}