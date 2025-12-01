package Sogeti.stepDefinitions;


import Sogeti.pages.AllPages;
import Sogeti.utilities.ConfigReader;
import Sogeti.utilities.Driver;
import Sogeti.utilities.ReusableMethods;
import io.cucumber.java.en.Given;

public class CommonSD {

    AllPages allPages =new AllPages();

    @Given("Der Benutzer geht zur Startseite")
    public void der_benutzer_geht_zur_startseite() {
        Driver.getDriver().get(ConfigReader.getProperty("url"));
        ReusableMethods.waitForVisibilityOfTitle("Sogeti");

    }



}
