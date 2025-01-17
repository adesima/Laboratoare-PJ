package exercitiul2;

import java.util.Arrays;
import java.util.Random;

public class Vers {
    private String vers;

    public Vers(String vers) {
        this.vers = vers;
    }

    public String getVers() {
        return vers;
    }

    public void setVers(String vers) {
        this.vers = vers;
    }

    // functie care determina numarul de cuvinte dintr-un vers
    public int cuvinte(String vers) {
        String[] cuvinte = vers.split(" ");
        int nr_cuvinte = 0;

        for (String cuvant : cuvinte)
            nr_cuvinte++;

        return nr_cuvinte;
    }

    // functie care determina numarul de vocale dintr-un vers
    public int vocale(String vers) {
        // impart fiecare vers in cuvinte
        String[] cuvinte = vers.split(" ");
        int nr_vocale = 0;
        String[] vocale = {"a", "e", "i", "o", "u", "A", "E", "I", "O", "U"};   // vector de voclae

        // impart fiecare cuvant in litere si verific apartenenta fiecarei litere la vectorul de vocale
        for (String cuvant : cuvinte) {
            String[] litere = cuvant.split("");
            for (String litera : litere)
                if (Arrays.asList(vocale).contains(litera)) nr_vocale++;
        }

        return nr_vocale;
    }

    // functie care returneaza steluta daca un vers se termina cu o grupare de cuvinte la alegere
    public String steluta(String vers, String grupare) {
        if (vers.endsWith(grupare)) return "*";
        else return " ";
    }

    // functie care scrie cu majuscule un vers daca un numar generat random este mai mic decat 0.1
    public String majuscule(String vers) {
        Random r = new Random();
        float numar = r.nextFloat(1);
        if (numar < 0.1)
            return vers.toUpperCase();
        else
            return vers;
    }
}
