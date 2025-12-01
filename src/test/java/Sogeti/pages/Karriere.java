package Sogeti.pages;

import Sogeti.utilities.*;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class Karriere {

    WebDriver driver = Driver.getDriver();

    public Karriere(){
        PageFactory.initElements(driver,this);
    }

    private By offeneStellen = By.xpath("//a[@aria-label='Offene Stellen Menü']");
    private By unsereFachbereiche = By.xpath("//a[@aria-label='Unsere Fachbereiche Menü']");
    private By karrierewege = By.xpath("//a[@aria-label='Karrierewege Menü']");
    private By guteGrundeFurSogeti = By.xpath("//a[@aria-label='Gute Gründe für Sogeti']");
    private By diversitaetUndInklusion = By.xpath("//a[@aria-label='Diversität und Inklusion']");
    private By bewerbungstippsFAQ = By.xpath("//a[@aria-label='Bewerbungstipps & FAQ']");



    //JavaScript wird verwendet, wenn Selenium den Klick aufgrund von Überlagerungen oder Elementunsichtbarkeit nicht durchführen kann.
    //(Türkçe Çevirisi: JavaScript, Selenium'un çakışmalar veya elementin görünmezliği nedeniyle tıklamayı gerçekleştiremediği durumlarda kullanılır.)

    public void moveToHeader(String karriereMenuName){
        ReusableMethods.waitForSeconds(1);
        By targetElement;

        switch (karriereMenuName){

            case "Offene Stellen":
                targetElement = offeneStellen;
                break;
            case "Unsere Fachbereiche":
                targetElement = unsereFachbereiche;
                break;
            case "Karrierewege":
                targetElement = karrierewege;
                break;
            case "Gute Gründe für Sogeti":
                targetElement = guteGrundeFurSogeti;
                break;
            case "Diversität und Inklusion": // 'überUns' yerine 'uberuns' kullanmak kodlama açısından daha temizdir.
                targetElement = diversitaetUndInklusion ;
                break;
            case "Bewerbungstipps & FAQ": // 'überUns' yerine 'uberuns' kullanmak kodlama açısından daha temizdir.
                targetElement = bewerbungstippsFAQ;
                break;
            default:
                throw new NoSuchElementException("Fehler!: Ungültige Menübezeichnung: " + karriereMenuName);
        }

        ReusableMethods.clickElementByJS(targetElement);
        ReusableMethods.waitForSeconds(3);
    }

}
