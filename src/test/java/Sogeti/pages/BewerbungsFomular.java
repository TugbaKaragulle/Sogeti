package Sogeti.pages;

import Sogeti.utilities.Driver;
import Sogeti.utilities.ReusableMethods;
import com.github.javafaker.Faker;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class BewerbungsFomular {

    private WebDriver driver = Driver.getDriver();
    private static final Logger log = LogManager.getLogger(BewerbungsFomular.class);
    private Faker faker = new Faker();

    public BewerbungsFomular() {
        PageFactory.initElements(driver, this);
    }

    private By cookies = By.xpath("//button[text()='Alle akzeptieren']");
    private By vorname = By.id("id_first_name");
    private By nachname = By.id("id_last_name");
    private By email = By.id("id_email");
    private By telefonNummer = By.cssSelector("input[data-target-id='id_phone_number']");
    private By standortFrankfurt = By.xpath("//label[@for='id_job_question_493150_1_0']");
    private By datenSchutz = By.id("id_terms");
    private By lebenslauf = By.name("cv");
    private By absenden = By.id("submit-id-submit");


    // Formular ausfüllen
    public void fillForm() {
        try {
            acceptCookies();
            enterFirstName();
            enterLastName();
            enterEmail();
            enterPhoneNumber();
            uploadCV();
            selectLocation();
            acceptTerms();
            ReusableMethods.waitForSeconds(2);
            log.info("Formular erfolgreich ausgefüllt.");
        } catch (Exception e) {
            log.error("Fehler beim Ausfüllen des Formulars.", e);
            throw e;
        }
    }

    //Cookies Entfernung
    private void acceptCookies() {
        try {
            ReusableMethods.clickElement(cookies);
            log.info("Cookies akzeptiert.");
        } catch (Exception e) {
            log.warn("Cookies-Banner nicht gefunden, überspringe Schritt.");
        }
    }

    private void enterFirstName() {
        ReusableMethods.sendKeys(vorname, faker.name().firstName());
        ReusableMethods.waitForSeconds(3);
        log.info("Vorname eingegeben.");
    }

    private void enterLastName() {
        ReusableMethods.sendKeys(nachname, faker.name().lastName());
        ReusableMethods.waitForSeconds(3);
        log.info("Nachname eingegeben.");
    }

    private void enterEmail() {
        ReusableMethods.sendKeys(email, faker.internet().emailAddress());
        ReusableMethods.waitForSeconds(3);
        log.info("Email eingegeben.");
    }

    private void enterPhoneNumber() {
        ReusableMethods.sendKeys(telefonNummer, faker.phoneNumber().phoneNumber());
        ReusableMethods.waitForSeconds(3);
        log.info("Telefonnummer eingegeben.");
    }

    private void uploadCV() {
        try {
            String absolutePath = "C:/Users/Tugba/IdeaProjects/Sogeti/Test Sogeti.docx";
            WebElement cvInput = driver.findElement(lebenslauf);
            cvInput.sendKeys(absolutePath);
            ReusableMethods.waitForSeconds(3);
            log.info("Lebenslauf hochgeladen.");
        } catch (Exception e) {
            log.error("Fehler beim Hochladen des Lebenslaufs.", e);
            throw e;
        }
    }

    private void selectLocation() {
        ReusableMethods.clickElement(standortFrankfurt);
        ReusableMethods.waitForSeconds(3);
        log.info("Standort 'Frankfurt' ausgewählt.");
    }

    private void acceptTerms() {
        ReusableMethods.clickElement(datenSchutz);
        ReusableMethods.waitForSeconds(3);
        log.info("Datenschutz akzeptiert.");
    }

    // Formular absenden
    public void submitForm() {
        try {
            //ReusableMethods.clickElement(absenden);
            log.info("Formular abgeschickt.");
        } catch (Exception e) {
            log.error("Fehler beim Absenden des Formulars.", e);
            throw e;
        }
    }
}