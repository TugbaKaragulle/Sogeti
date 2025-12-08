package Sogeti.pages;

import Sogeti.utilities.Driver;
import Sogeti.utilities.JavascriptUtils;
import Sogeti.utilities.ReusableMethods;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;

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
    private By karriere = By.xpath("//a[@aria-label='Karriere Menü']");
    private By uberUns = By.xpath("//a[@aria-label='Über uns Menü']/span/span");
    private By shadowhost = By.cssSelector("aside#usercentrics-cmp-ui");
    private By cookiesDenyButton = By.cssSelector("button.deny.uc-deny-button");

    //Methods

    public void removeCookies() {
        ReusableMethods.waitForSeconds(4);
        try {
            WebElement shadow = driver.findElement(shadowhost);
            SearchContext shadowRoot = shadow.getShadowRoot();
            WebElement denyButton = shadowRoot.findElement(cookiesDenyButton);
            denyButton.click();
            log.info("Cookies-Banner erfolgreich abgelehnt");
        } catch (NoSuchElementException e) {
            log.warn("Cookies-Banner nicht gefunden, überspringe Schritt");
        }
    }

    public boolean isHeaderDisplayed(String headerName) {
        By locator = getHeaderLocator(headerName);
        boolean displayed = ReusableMethods.isDisplayed(locator);
        log.info("Header '{}' sichtbar: {}", headerName, displayed);
        return displayed;
    }

    public void hoverOverHeader(String headerName) {
        By locator = getHeaderLocator(headerName);
        WebElement element = ReusableMethods.visibilityOfElement(locator);

        try {
            // 1) Normal hover
            Actions actions = new Actions(driver);
            actions.moveToElement(element).pause(Duration.ofMillis(300)).perform();
            log.info("Hover durchgeführt für '{}' ", headerName);

        } catch (Exception e) {
            log.warn("Normaler Hover fehlgeschlagen – JS-MouseOver wird verwendet: {}", headerName);

            // 2) Headless fallback: JS mouseover event
            JavascriptUtils.mouseOverByJS(element);
        }
    }

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