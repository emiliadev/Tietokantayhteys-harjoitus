package datat;

public class Materiaali {

    private int materiaaliID;
    private String kuvaus;
    private String koostumus;
    private double hinta;

    //konstruktori 1
    public Materiaali(int materiaaliID, String kuvaus, String koostumus, double hinta) {
        this.materiaaliID = materiaaliID;
        this.kuvaus = kuvaus;
        this.koostumus = koostumus;
        this.hinta = hinta;
    }

    //konstruktori 2
    public Materiaali(String kuvaus, String koostumus, double hinta) {
        this.kuvaus = kuvaus;
        this.koostumus = koostumus;
        this.hinta = hinta;
    }

    //metodit
    public int getMateriaaliID() {
        return materiaaliID;
    }

    public void setMateriaaliID(int materiaaliID) {
        this.materiaaliID = materiaaliID;
    }

    public String getKuvaus() {
        return kuvaus;
    }

    public void setKuvaus(String kuvaus) {
        this.kuvaus = kuvaus;
    }

    public String getKoostumus() {
        return koostumus;
    }

    public void setKoostumus(String koostumus) {
        this.koostumus = koostumus;
    }

    public double getHinta() {

        return hinta;
    }

    public void setHinta(double hinta) {
        this.hinta = hinta;
    }

    @Override
    public String toString() {
        return "MATERIAALI: mat.ID = " + materiaaliID + "; " + kuvaus + "; " + koostumus + "; hinta = " + hinta + " €/m\n";
    }

}
