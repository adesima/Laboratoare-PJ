package Tema.problema2;

import java.time.LocalDate;

public class Produs {
    private String denumire;
    private float pret;
    private int cantitate;
    private LocalDate data_exp;
    private static float incasari = 0;

    public Produs(String denumire, float pret, int cantitate, LocalDate data_exp) {
        this.denumire = denumire;
        this.pret = pret;
        this.cantitate = cantitate;
        this.data_exp = data_exp;
    }

    public String getDenumire() {
        return denumire;
    }

    public int getCantitate() {
        return cantitate;
    }

    public float getPret() {
        return pret;
    }

    public void setCantitate(int cantitate) {
        this.cantitate = cantitate;
    }

    public LocalDate getData_exp() {
        return data_exp;
    }

    public static float getIncasari() {
        return incasari;
    }

    public static void setIncasari(float incasari) {
        Produs.incasari = incasari;
    }

    @Override
    public String toString() {
        return denumire + ", " +
                pret + " lei, " +
                cantitate + " bucati, " +
                "EXP: " + data_exp;
    }
}
