package Sogeti.stepDefinitions;


import Sogeti.pages.AllPages;
import Sogeti.utilities.ConfigReader;
import Sogeti.utilities.Driver;
import Sogeti.utilities.ReusableMethods;
import io.cucumber.java.en.*;


public class CommonSD {

    AllPages allPages =new AllPages();

    @Given("Der Benutzer geht zur Startseite")
    public void der_benutzer_geht_zur_startseite() {
        Driver.getDriver().get(ConfigReader.getProperty("url"));
        ReusableMethods.waitForVisibilityOfTitle("Sogeti");
    }

    @When("Entfernt das Cookies-Banner")
    public void entfernt_das_cookies_banner() {
        allPages.getHomePage().removeCookies();
    }
}
