package exercitiul3;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;

enum Orientare{LUNGIME, LATIME, ORICARE}

public class Placa {
    //JsonProperty da link intre clasa si visier(variabila de aici si cea din json)
    @JsonProperty("descriere")
    private String descriere;
    @JsonProperty("lungime")
    private int lungime;
    @JsonProperty("latime")
    private int latime;
    @JsonProperty("orientare")
    private Orientare orientare;
    @JsonProperty("canturi")
    private boolean[] caricaturi;
    @JsonProperty("nr_bucati")
    private int nrBucati;

    public Placa(){}

    public Placa(String descriere, int lungime, int latime, Orientare orientare, boolean[] caricaturi, int nrBucati) {
        this.descriere = descriere;
        this.lungime = lungime;
        this.latime = latime;
        this.orientare = orientare;
        this.caricaturi = caricaturi;
        this.nrBucati = nrBucati;
    }

    public String getDescriere() {
        return descriere;
    }

    public void setDescriere(String descriere) {
        this.descriere = descriere;
    }

    public int getLungime() {
        return lungime;
    }

    public void setLungime(int lungime) {
        this.lungime = lungime;
    }

    public int getLatime() {
        return latime;
    }

    public void setLatime(int latime) {
        this.latime = latime;
    }

    public Orientare getOrientare() {
        return orientare;
    }

    public void setOrientare(Orientare orientare) {
        this.orientare = orientare;
    }

    public boolean[] getCaricaturi() {
        return caricaturi;
    }

    public void setCaricaturi(boolean[] caricaturi) {
        this.caricaturi = caricaturi;
    }

    public int getNrBucati() {
        return nrBucati;
    }

    public void setNrBucati(int nrBucati) {
        this.nrBucati = nrBucati;
    }
    public int getArie() {
        return lungime * latime;
    }

    @Override
    public String toString() {
        return descriere + ", L = " + lungime + " cm, l = " + latime +
                " cm, orientare=" + orientare + ", caricaturi=" + Arrays.toString(caricaturi) + ", " + nrBucati + " buc";
    }
}
