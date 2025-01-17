package exercitiul2;

import java.util.Objects;

enum TipChitara{Electrica, Acustica, Clasica}

public class Chitara extends InstrumentMuzical{
    private TipChitara tipChitara;
    private int nrCorzi;

    public Chitara(){}
    public Chitara(String producator, double pret, TipChitara tipChitara, int nrCorzi) {
        super(producator, pret);
        this.tipChitara = tipChitara;
        this.nrCorzi = nrCorzi;
    }

    public TipChitara getTipChitara() {
        return tipChitara;
    }

    public void setTipChitara(TipChitara tipChitara) {
        this.tipChitara = tipChitara;
    }

    public int getNrCorzi() {
        return nrCorzi;
    }

    public void setNrCorzi(int nrCorzi) {
        this.nrCorzi = nrCorzi;
    }

    @Override
    public String toString() {
        return super.toString() + "tipChitara=" + tipChitara + ", nrCorzi=" + nrCorzi + '}';
    }
    //compara clasele ca sa vada daca sunt 2 identice
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Chitara)) return false;
        if (!super.equals(o)) return false;
        Chitara chitara = (Chitara) o;
        return nrCorzi == chitara.nrCorzi &&
                tipChitara == chitara.tipChitara;
    }
    //face hash pt fiecare variabila , nevoie de el ca sa mearga hashset-u
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), tipChitara, nrCorzi);
    }
}
