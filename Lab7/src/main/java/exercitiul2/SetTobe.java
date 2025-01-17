package exercitiul2;

import java.util.Objects;

enum TipTobe{Electronice, Acustice}

public class SetTobe extends InstrumentMuzical{
    private TipTobe tipTobe;
    private int nrTobe;
    private int nrCinele;

    public SetTobe(){}
    public SetTobe(String producator, double pret, TipTobe tipTobe, int nrTobe, int nrCinele) {
        super(producator, pret);
        this.tipTobe = tipTobe;
        this.nrTobe = nrTobe;
        this.nrCinele = nrCinele;
    }

    public TipTobe getTipTobe() {
        return tipTobe;
    }

    public void setTipTobe(TipTobe tipTobe) {
        this.tipTobe = tipTobe;
    }

    public int getNrTobe() {
        return nrTobe;
    }

    public void setNrTobe(int nrTobe) {
        this.nrTobe = nrTobe;
    }

    public int getNrCinele() {
        return nrCinele;
    }

    public void setNrCinele(int nrCinele) {
        this.nrCinele = nrCinele;
    }

    @Override
    public String toString() {
        return super.toString() + "tipTobe=" + tipTobe + ", nrTobe=" + nrTobe + ", nrCinele=" + nrCinele + '}';
    }
    //compara clasele ca sa vada daca sunt 2 identice
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SetTobe)) return false;
        if (!super.equals(o)) return false;
        SetTobe setTobe = (SetTobe) o;
        return nrTobe == setTobe.nrTobe &&
                nrCinele == setTobe.nrCinele &&
                tipTobe == setTobe.tipTobe;
    }
    //face hash pt fiecare variabila , nevoie de el ca sa mearga hashset-u
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), tipTobe, nrTobe, nrCinele);
    }
}
