package exercitiul2;

import java.util.Objects;

public class InstrumentMuzical {
    protected String producator;
    protected double pret;

    public InstrumentMuzical(){}
    public InstrumentMuzical(String producator, double pret) {
        this.producator = producator;
        this.pret = pret;
    }

    public String getProducator() {
        return producator;
    }

    public void setProducator(String producator) {
        this.producator = producator;
    }

    public double getPret() {
        return pret;
    }

    public void setPret(double pret) {
        this.pret = pret;
    }

    @Override
    public String toString() {
        return "InstrumentMuzical{" +
                "producator='" + producator + '\'' +
                ", pret=" + pret +
                '}';
    }

    //compara clasele ca sa vada daca sunt 2 identice
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InstrumentMuzical that = (InstrumentMuzical) o;
        return Double.compare(that.pret, pret) == 0 && Objects.equals(producator, that.producator);
    }
    //face hash pt fiecare variabila , nevoie de el ca sa mearga hashset-u
    @Override
    public int hashCode() {
        return Objects.hash(producator, pret);
    }
}
