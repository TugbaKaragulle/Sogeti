package Sogeti.pages;

public class AllPages {

    private HomePage homePage;
    private Karriere karriere;
    private OffeneStellen offeneStellen;
    private BewerbungsFomular bewerbungsFomular;

    public HomePage getHomePage() {
        if (homePage == null) {
            homePage = new HomePage();
        }
        return homePage;
    }

    public Karriere getKarriere(){

        if (karriere == null) {
            karriere = new Karriere();
        }
        return karriere;
    }

    public OffeneStellen getOffeneStellen(){
        if (offeneStellen ==null){
            offeneStellen = new OffeneStellen();
        }
        return offeneStellen;
    }

    public BewerbungsFomular getBewerbungsFomular(){
        if (bewerbungsFomular == null){
            bewerbungsFomular = new BewerbungsFomular();
        }
        return bewerbungsFomular;
    }

}
