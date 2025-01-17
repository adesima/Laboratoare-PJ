package Tema.problema2;

/*
2. Să se scrie un program care citește din fișierul produse.csv informații referitoare la
produsele dintr-un magazin. Pe fiecare linie se află: denumirea produsului, preţul (număr real)
cantitatea (număr întreg), data expirării (LocalDate). Cele patru elemente sunt separate prin
caracterul "," (zahar, 1.5, 50, 2024-02-25). Pentru fiecare rând citit se va crea un obiect de tip
Produs care se va adăuga unei colecții de obiecte de tip List. Se va defini o clasă Produs care
va conține: variabile membre private corespunzătoare celor trei elemente care descriu un produs,
cel puţin un constructor, gettere si settere în funcție de necesități şi redefinirea metodei toString()
din clasa Object, metodă care va fi utilizată pentru afișarea produselor.

Să se realizeze un program care va dispune de un meniu și va implementa următoarele
cerințe:
a) afișarea tuturor produselor din colecția List
b) afișarea produselor expirate
c) vânzarea unui produs, care se poate realiza doar dacă există suficientă cantitate pe stoc.
Daca produsul ajunge la cantitate zero, se elimina din listă. În clasa Produs se va declara
o variabilă statica încasări care se va actualiza la fiecare vânzare a unui produs, luând în
considerare prețul produsului vândut şi cantitatea vândută .
d) afișarea produselor cu prețul minim (pot fi mai multe cu același preț)
e) salvarea produselor care au o cantitate mai mică decât o valoare citită de la tastatură
într-un fișier de ieșire.
*/

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.Collectors;

public class MainApp {
    public static void meniu() {
        System.out.println("1. Afisare produse");
        System.out.println("2. Afisare produse expirate");
        System.out.println("3. Vanzarea unui produs");
        System.out.println("4. Afisare pret minim");
        System.out.println("5. Filtru cantitate maxima");
        System.out.print("Optiunea dvs. este: ");
    }

    public static void citire(List<Produs> produse) {
        BufferedReader flux_in;
        try {
            flux_in = new BufferedReader(new InputStreamReader(new FileInputStream("produse.csv")));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        String line;
        try {
            while ((line = flux_in.readLine()) != null) {
                String[] data = line.split(",");
                String[] data_exp = data[3].split("-");
                Produs p = new Produs(data[0], Float.parseFloat(data[1]), Integer.parseInt(data[2]), LocalDate.of(Integer.parseInt(data_exp[0]), Integer.parseInt(data_exp[1]), Integer.parseInt(data_exp[2])));
                produse.add(p);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Datele au fost citite cu succes din fisier.\n");
    }

    // a) afișarea tuturor produselor din colecția List
    public static void afisare(List<Produs> produse) {
        for (Produs produs : produse)
            System.out.println(produs);
    }

    // b) afișarea produselor expirate
    public static void afisare_exp(List<Produs> produse) {
        for (Produs produs : produse)
            if (produs.getData_exp().getYear() < LocalDate.now().getYear())
                System.out.println(produs);
            else if (produs.getData_exp().getYear() == LocalDate.now().getYear())
                    if (produs.getData_exp().getMonthValue() < LocalDate.now().getMonthValue())
                        System.out.println(produs);
                    else if (produs.getData_exp().getMonthValue() == LocalDate.now().getMonthValue())
                        if (produs.getData_exp().getDayOfMonth() <= LocalDate.now().getDayOfMonth())
                            System.out.println(produs);
    }

    // c) vânzarea unui produs, care se poate realiza doar dacă există suficientă cantitate pe stoc.
    //Daca produsul ajunge la cantitate zero, se elimina din listă. În clasa Produs se va declara
    //o variabilă statica încasări care se va actualiza la fiecare vânzare a unui produs, luând în
    //considerare prețul produsului vândut şi cantitatea vândută .
    public static void vanzare(List<Produs> produse) {
        String nume_produs;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ce produs doriti sa vindeti?");
        nume_produs = scanner.next();
        int gasit = 0, vandut = 0;
        for (Produs produs : produse) {
            if (Objects.equals(produs.getDenumire(), nume_produs)) {
                gasit = 1;
                System.out.print("Cantitatea dorita: ");
                int cantitate = scanner.nextInt();
                if (produs.getCantitate() >= cantitate) {
                    produs.setCantitate(produs.getCantitate() - cantitate);
                    vandut = 1;
                    Produs.setIncasari(Produs.getIncasari() + cantitate * produs.getPret());
                }
            }
        }
        if (gasit == 0)
            System.out.println("Produsul cautat nu exista!");
        if (vandut == 0)
            System.out.println("Cantitate insuficienta!");
    }

    // d) afișarea produselor cu prețul minim (pot fi mai multe cu același preț)
    public static void pret_minim(List<Produs> produse) {
        double pretMinim = produse.stream().mapToDouble(Produs::getPret).min().orElse(0);
        produse.stream().filter(p -> p.getPret() == pretMinim).forEach(System.out::println);
    }

    // e) salvarea produselor care au o cantitate mai mică decât o valoare citită de la tastatură
    // intr-un fisier
    public static void salvare_dupa_cantitate(List<Produs> produse) {
        System.out.print("Introduceti valoarea cantitatii maxime: ");
        Scanner scanner = new Scanner(System.in);
        int limita = scanner.nextInt();

        List<Produs> produseFiltrate = produse.stream().filter(p -> p.getCantitate() < limita).collect(Collectors.toList());
        //se aplica un filtru pe fluxul de produse pentru a pastra doar acele produse a caror cantitate este mai mica decat valoarea variabilei limita
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("produse_filtrate.csv"))) {
            for (Produs produs : produseFiltrate) {
                bw.write(String.format("%s,%f,%d,%s%n",
                        produs.getDenumire(), produs.getPret(), produs.getCantitate(), produs.getData_exp()));
            }
            System.out.println("Produsele au fost salvate în fișierul produse_filtrate.csv.");
        } catch (IOException e) {
            System.err.println("Eroare la scrierea fișierului: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        List<Produs> produse = new ArrayList<>();
        citire(produse);

        while (true) {
            meniu();
            int opt;
            Scanner scanner = new Scanner(System.in);
            opt = scanner.nextInt();

            switch (opt) {
                case 1:
                    afisare(produse);
                    break;
                case 2:
                    afisare_exp(produse);
                    break;
                case 3:
                    vanzare(produse);
                    break;
                case 4:
                    pret_minim(produse);
                    break;
                case 5:
                    salvare_dupa_cantitate(produse);
                    break;
                default:
                    System.out.println("Optiune invalida! Va rog sa introduceti un numar de la 1 la 5.");
                    break;
            }
        }
    }
}
