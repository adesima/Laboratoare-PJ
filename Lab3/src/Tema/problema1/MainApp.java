package Tema.problema1;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/*
1. Funcţia f(x) = ax^2 + bx + c are ca grafic o parabolă cu vârful de coordonate
(-b/2a; -b^2+4ac/4a)

Se cere să se definească o clasă Parabola ai cărei membri vor fi:
• 3 variabile de tip int care reprezintă coeficienţii a, b şi c
• un constructor cu 3 parametrii de tip int
• o metodă care calculează și returnează vârful parabolei
• se va redefini metoda toString() din clasa Object, astfel încât să returneze un String de
forma f(x) = a x^2 + b x + c. Caracteristicile a, b și c ale parabolei se vor înlocui cu
valorile efective. Metoda va fi utilizată pentru afișarea parabolei sub forma unei funcții
• o metodă care primește ca și parametru de intrare o parabolă și returneză coordonatele
mijlocului segmentului de dreptă care uneşte vârful parabolei curente cu varful
parabolei transmisă ca și parametru de intrare
• o metodă statică ce primeşte ca parametri de intrare două parabole şi calculează
coordonatele mijlocului segmentului de dreptă care uneşte vârfurile celor două parabole.
• o metodă care primește ca și parametru de intrare o parabolă și returneză lungimea
segmentului de dreptă care unește vârful parabolei curente cu varful parabolei transmisă
ca și parametru de intrare. Se va utiliza metoda statică Math.hypot
• o metodă statică care primește ca și parametri de intrare două parabole și calculează
lungimea segmentului de dreapta ce unește vârfurile celor două parabole. Se va utiliza
metoda statică Math.hypot

Coordonatele mijlocului segmentului de drepta care uneste varfurile a două parabole se
calculează în felul următor:
x = x1 + x2 / 2, y = y1 + y2 / 2, unde (x1, y1) sunt coordonatele vârfului
primei parabole, iar (x2, y2) descriu vârful celei de a doua parabole.

Lungimea segmentului care unește vârfurile a două parabole se calculează în felul
următor:
sqrt( (x2 -  x1)^2 + (y2 - y1)^2) )

Fișierul de intrare in.txt conține mai multe linii, pe fiecare linie aflându-se coeficienții
unei parabole.
Să se citească informația din fișierul de intrare și pentru fiecare linie să se creeze
câte un obiect de tip Parabola care se va adăuga unei colecții de tip List.
Colecția va fi apoi traversată și pentru fiecare element se va afișa parabola sub forma unei funcții
și apoi vârful parabolei.
Pentru două parabole din colecție se va afișa mijlocul segmentului care le unește vârfurile și lungimea
segmentului care le unește vârfurile apelând metodele dezvoltate în clasa Parabol
*/

public class MainApp {
    public static void main(String[] args) {
        /*Parabola p = new Parabola(1, 2, 3);
        System.out.println(p);*/

        List<Parabola> parabole = new ArrayList<>();
        int a, b, c;
        String line;
        BufferedReader flux_in;

        try {
            flux_in = new BufferedReader(new InputStreamReader(new FileInputStream("in.txt")));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        try {
            while ((line = flux_in.readLine()) != null) {
                String[] coef = line.split(" ");
                a = Integer.parseInt(coef[0]);
                b = Integer.parseInt(coef[1]);
                c = Integer.parseInt(coef[2]);

                Parabola p = new Parabola(a, b, c);
                parabole.add(p);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        for (Parabola parabola : parabole)
            System.out.println(parabola);

        System.out.print("Coordonatele mijlocului segmentului care uneste varfurile a doua parabole, folosind metoda non statica: ");
        System.out.println(parabole.getFirst().MijlocSegmentCareUnesteVarfurile(parabole.getLast()));
        System.out.print("Coordonatele mijlocului segmentului care uneste varfurile a doua parabole, folosind metoda statica: ");
        System.out.println(Parabola.MijlocSegmentCareUnesteVarfurile(parabole.getFirst(), parabole.getLast()));

        System.out.print("Lungimea segmentului care uneste varfurile a doua parabole, folosind metoda non statica: ");
        System.out.println(parabole.getFirst().LungimeSegmentCareUnesteVarfurile(parabole.getLast()));
        System.out.print("Lungimea segmentului care uneste varfurile a doua parabole, folosind metoda statica: ");
        System.out.println(Parabola.LungimeSegmentCareUnesteVarfurile(parabole.getFirst(), parabole.getLast()));
    }
}
