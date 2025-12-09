   @Regression
  Feature: Bewerbung über die Homepage von Sogeti

  Background: Benutzer öffnet die Startseite
    Given Der Benutzer geht zur Startseite
    And Entfernt das Cookies-Banner

    @Bewerbung @E2E
  Scenario: Job suchen und Bewerbung starten
    When Der Benutzer fährt mit der Maus über den Menüpunkt "Karriere"
    And Klickt auf den Link "Offene Stellen"
    And Scrollt langsam bis zum Ende der Stellenanzeigen
    And Gibt im Suchfeld den Begriff "Test" ein und drückt Enter
    And Wählt im Filter Standort "Frankfurt am Main" aus
    And Wählt im Filter Vertragsart "Full-time" aus
    And Klickt auf den ersten Jobtitel, der "Test" enthält
    And Klickt auf Jetzt bewerben
    And Füllt das Bewerbungsformular aus
    Then Prüft, ob der Absenden-Button klickbar ist




