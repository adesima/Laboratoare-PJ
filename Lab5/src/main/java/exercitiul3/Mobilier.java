package exercitiul3;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Mobilier {
    //JsonProperty da link intre clasa si visier(variabila de aici si cea din json)
    @JsonProperty("nume")
    private String nume;
    @JsonProperty("placi")
    private List<Placa> placi;


    public Mobilier(){}

    public Mobilier(String nume, List<Placa> placi) {
        this.nume = nume;
        this.placi = placi;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public List<Placa> getPlaci() {
        return placi;
    }

    public void setPlaci(List<Placa> placi) {
        this.placi = placi;
    }

    @Override
    public String toString() {
        return nume + ": " + placi;
    }
}
