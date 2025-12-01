package Sogeti.stepDefinitions;

import Sogeti.pages.AllPages;
import Sogeti.utilities.ReusableMethods;
import io.cucumber.java.en.*;

public class US01_BewerbungSD {

    AllPages allPages =new AllPages();

    @When("Der Benutzer mit der Maus über den Menüpunkt {string} fährt")
    public void der_benutzer_mit_der_maus_über_den_menüpunkt_fährt(String title) {
        allPages.getHomePage().moveToHeader(title);

    }
    @And("Auf den Link {string} klickt")
    public void auf_den_link_klickt(String menuName) {
        allPages.getKarriere().moveToHeader(menuName);
        ReusableMethods.waitForSeconds(2);
    }
    @And("Der Benutzer langsam bis zum Ende aller Stellenanzeigen scrollt")
    public void der_benutzer_langsam_bis_zum_ende_aller_stellenanzeigen_scrollt() {
        allPages.getOffeneStellen().scrollToElement();
    }
    @When("Der Benutzer im Suchfeld den Begriff {string} eingibt und die Enter-Taste drückt")
    public void der_benutzer_im_suchfeld_den_begriff_eingibt_und_die_enter_taste_drückt(String auftragsName) {
        allPages.getOffeneStellen().jobSuche(auftragsName);
    }

    @Given("Der Benutzer im Filter Standort den Wert Frankfurt am Main auswählt")
    public void der_benutzer_im_filter_standort_den_wert_frankfurt_am_main_auswählt() {
        allPages.getOffeneStellen().waehleStandort();
    }

    @Given("Der Benutzer im Filter Vertragsart den Wert full-time auswählt")
    public void der_benutzer_im_filter_vertragsart_den_wert_full_time_auswählt() {
        allPages.getOffeneStellen().waehleVertragsart();

    }
    @Given("Klickt der Benutzer auf den ersten gefundenen Jobtitel, der {string} enthält")
    public void klickt_der_benutzer_auf_den_ersten_gefundenen_jobtitel_der_enthält(String wort) {
        allPages.getOffeneStellen().waehleJob(wort);
    }
    @Given("Der Benutzer klickt jetzt bewerben")
    public void der_benutzer_klickt_jetzt_bewerben() {
        allPages.getOffeneStellen().klickJetztBewerben();

    }
    @Given("Füllt den Formular aus")
    public void füllt_den_formular_aus() {
        allPages.getBewerbungsFomular().formullarAusfüllen();

    }
    @Given("Klickt absenden")
    public void klickt_absenden() {


    }

}
