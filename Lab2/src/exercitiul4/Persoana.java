package exercitiul4;

import java.time.LocalDate;

public class Persoana {
    private String nume;
    private String cnp;

    public Persoana(String nume, String cnp) {
        this.nume = nume;
        this.cnp = cnp;
    }

    public int getVarsta() {
        int varsta = 0;

        // Citire data curenta
        LocalDate data_curenta = LocalDate.now();

        // Extragere data nasterii din CNP
        int an, luna, zi;
        an = (cnp.charAt(1) - 48) * 10 + (cnp.charAt(2) - 48);
        if (an > 24)
            an += 1900;
        else an += 2000;
        luna = (cnp.charAt(3) - 48) * 10 + (cnp.charAt(4) - 48);
        zi = (cnp.charAt(5) - 48) * 10 + (cnp.charAt(6) - 48);
        LocalDate data_nasterii = LocalDate.of(an, luna, zi);

        // Calcul varsta
        varsta = data_curenta.getYear() - data_nasterii.getYear() - 1;
        if (data_curenta.getMonthValue() > data_nasterii.getMonthValue())
            varsta++;
        else if (data_curenta.getMonthValue() == data_nasterii.getMonthValue())
            if (data_curenta.getDayOfMonth() >= data_nasterii.getDayOfMonth())
                varsta++;

        return varsta;
    }

    @Override
    public String toString() {
        return nume + ", " + cnp + ", " + getVarsta() + " ani";
    }
}
