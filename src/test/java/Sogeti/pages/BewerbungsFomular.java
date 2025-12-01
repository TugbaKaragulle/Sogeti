package Sogeti.pages;

import Sogeti.utilities.*;
import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class BewerbungsFomular {

    WebDriver driver = Driver.getDriver();

    public BewerbungsFomular(){
        PageFactory.initElements(driver,this);
    }

    private By vorname = By.xpath("//input[@id='id_first_name']");
    private By nachname = By.xpath("//input[@id='id_last_name']");
    private By email = By.xpath("//input[@id='id_email']");
    private By telefonNummer = By.xpath("//input[@data-target-id='id_phone_number']");
    private By standort = By.xpath("//label[@for='id_job_question_493150_1_0']");
    private By datenSchutz = By.xpath("//input[@id='id_terms']");
    private By absenden = By.id("submit-id-submit");
    private By lebenslauf = By.xpath("//input[@name='cv']");


    Faker faker = new Faker();

    public void formullarAusfüllen(){
        ReusableMethods.sendKeys(vorname,faker.name().name());
        ReusableMethods.waitForSeconds(2);
        ReusableMethods.sendKeys(nachname,faker.name().lastName());
        ReusableMethods.waitForSeconds(2);
        ReusableMethods.sendKeys(email,faker.internet().emailAddress());
        ReusableMethods.waitForSeconds(2);
        ReusableMethods.sendKeys(telefonNummer,faker.phoneNumber().phoneNumber());
        ReusableMethods.waitForSeconds(2);

    }


}
