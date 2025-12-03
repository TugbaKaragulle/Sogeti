package Sogeti.stepDefinitions;

import Sogeti.pages.AllPages;
import io.cucumber.java.en.*;
import org.testng.Assert;

public class US01_BewerbungSD {

    AllPages allPages = new AllPages();

    @When("Der Benutzer fährt mit der Maus über den Menüpunkt {string}")
    public void der_benutzer_fährt_mit_der_maus_über_den_menüpunkt(String menu) {
        allPages.getHomePage().hoverOverHeader(menu); // HomePage'deki yeni method
    }
    @And("Klickt auf den Link {string}")
    public void klickt_auf_den_link(String menuName) {
        allPages.getKarriere().clickCareerMenu(menuName); // Karriere sayfasındaki yeni method
    }
    @And("Scrollt langsam bis zum Ende der Stellenanzeigen")
    public void scrollt_langsam_bis_zum_ende_der_stellenanzeigen() {
        allPages.getOffeneStellen().scrollAndLoadMore();
    }
    @And("Gibt im Suchfeld den Begriff {string} ein und drückt Enter")
    public void gibt_im_suchfeld_den_begriff_ein_und_drückt_enter(String jobName) {
        allPages.getOffeneStellen().searchJob(jobName);
    }
    @And("Wählt im Filter Standort {string} aus")
    public void wählt_im_filter_standort_aus(String string) {
        allPages.getOffeneStellen().selectLocation();
    }
    @And("Wählt im Filter Vertragsart {string} aus")
    public void wählt_im_filter_vertragsart_aus(String string) {
        allPages.getOffeneStellen().selectContractType();
    }
    @And("Klickt auf den ersten Jobtitel, der {string} enthält")
    public void klickt_auf_den_ersten_jobtitel_der_enthält(String keyword) {
        allPages.getOffeneStellen().selectJob(keyword);
    }
    @And("Klickt auf Jetzt bewerben")
    public void klickt_auf_jetzt_bewerben() {
        allPages.getOffeneStellen().clickApplyNow();
    }
    @And("Füllt das Bewerbungsformular aus")
    public void füllt_das_bewerbungsformular_aus() {
        allPages.getBewerbungsFomular().fillForm();
    }

    @Then("Prüft, ob der Absenden-Button klickbar ist")
    public void prüft_ob_der_absenden_button_klickbar_ist() {
        Assert.assertTrue(allPages.getBewerbungsFomular().isAbsendenButtonClickable());

    }


}