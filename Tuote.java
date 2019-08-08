package datat;

public class Tuote {

    private String tuoteID;
    private String valmistajaID;
    private int materiaaliID;
    private String kokolajitelma;
    private String tuotetyyppi;
    private String sesonki;
    private Materiaali matsku;

    //konstruktori 1, jossa mukana materiaalit
    public Tuote(Materiaali materiat, String tuoteID, String valmistajaID, int materiaaliID, String tuotetyyppi, String kokolajitelma, String sesonki) {
        this.matsku = materiat;
        this.tuoteID = tuoteID;
        this.valmistajaID = valmistajaID;
        this.tuotetyyppi = tuotetyyppi;
        this.kokolajitelma = kokolajitelma;
        this.sesonki = sesonki;
    }

    //konstruktori 2
    public Tuote(String tuoteID, String valmistajaID, int materiaaliID, String tuotetyyppi, String kokolajitelma, String sesonki) {
        this.tuoteID = tuoteID;
        this.valmistajaID = valmistajaID;
        this.materiaaliID = materiaaliID;
        this.tuotetyyppi = tuotetyyppi;
        this.kokolajitelma = kokolajitelma;
        this.sesonki = sesonki;
    }

    //metodit
    public String getTuoteID() {
        return tuoteID;
    }

    public void setTuoteID(String tuoteID) {
        this.tuoteID = tuoteID;
    }

    public int getMateriaaliID() {
        return materiaaliID;
    }

    public void setMateriaaliID(int materiaaliID) {
        this.materiaaliID = materiaaliID;
    }

    public String getValmistajaID() {
        return valmistajaID;
    }

    public void setValmistajaID(String valmistajaID) {
        this.valmistajaID = valmistajaID;
    }

    public String getKokolajitelma() {
        return kokolajitelma;
    }

    public void setKokolajitelma(String kokolajitelma) {
        this.kokolajitelma = kokolajitelma;
    }

    public String getTuotetyyppi() {
        return tuotetyyppi;
    }

    public void setTuotetyyppi(String tuotetyyppi) {
        this.tuotetyyppi = tuotetyyppi;
    }

    public String getSesonki() {
        return sesonki;
    }

    public void setSesonki(String sesonki) {
        this.sesonki = sesonki;
    }

    public Materiaali getMatsku() {
        return matsku;
    }

    public void setMatsku(Materiaali matsku) {
        this.matsku = matsku;
    }
    

    @Override
    public String toString() {
        return "TUOTE: " + tuoteID + "; " + tuotetyyppi + "; valmistaja " + valmistajaID + "; mat.ID = " + materiaaliID + "; " + kokolajitelma + "; " + sesonki + "\n";
    }

}
