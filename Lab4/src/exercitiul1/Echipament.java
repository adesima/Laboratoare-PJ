package exercitiul1;

enum Situatie {achizitionat, expus, vandut};

public class Echipament {
    private String denumire;
    private int nr_inv;
    private double pret;
    private String zona_mag;
    private Situatie situatie;
    private String tip;

    public Echipament(String denumire, int nr_inv, double pret, String zona_mag, Situatie situatie, String tip) {
        this.denumire = denumire;
        this.nr_inv = nr_inv;
        this.pret = pret;
        this.zona_mag = zona_mag;
        this.situatie = situatie;
        this.tip = tip;
    }

    public String getDenumire() {
        return denumire;
    }

    public int getNr_inv() {
        return nr_inv;
    }

    public double getPret() {
        return pret;
    }

    public String getZona_mag() {
        return zona_mag;
    }

    public Situatie getSituatie() {
        return situatie;
    }

    public String getTip() {
        return tip;
    }

    public void setDenumire(String denumire) {
        this.denumire = denumire;
    }

    public void setNr_inv(int nr_inv) {
        this.nr_inv = nr_inv;
    }

    public void setPret(double pret) {
        this.pret = pret;
    }

    public void setZona_mag(String zona_mag) {
        this.zona_mag = zona_mag;
    }

    public void setSituatie(Situatie situatie) {
        this.situatie = situatie;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    @Override
    public String toString() {
        return tip + ": " +
                denumire + ", "
                + nr_inv + ", " +
                pret + " lei, " +
                zona_mag + ", " +
                situatie;
    }
}
