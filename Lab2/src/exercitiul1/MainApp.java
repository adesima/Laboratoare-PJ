package exercitiul1;

/*1. Fișierul judete_in.txt, conține lista neordonată a județelor din țară. Să se încarce
datele din fișier într-un tablou de String-uri și să se ordoneze acest tablou cu ajutorul metodei
sort() din clasa Arrays. Să se returneze pe ce poziție se află în vectorul ordonat un județ
introdus de la tastatură. Se va utiliza metoda de căutare binară din clasa Arrays.*/

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) throws IOException {
        try {
            // Folosesc un obiect de tip BufferedReader pentru citirea din fisier
            BufferedReader flux_in = new BufferedReader(new InputStreamReader(new FileInputStream("judete_in.txt")));

            // Salvez initial judetele intr-o lista temporara, deoarece nu stiu care va fi dimensiunea vectorului
            List<String> lista = new ArrayList<>();
            String data;
            while((data = flux_in.readLine()) != null) {
                lista.add(data);
            }

            System.out.println("Au fost gasite judetele: " + lista);

            // Convertesc lista într-un vector
            String[] judete = lista.toArray(new String[0]);

            // Sortez vectorul
            Arrays.sort(judete);

            // Afisez elementele sortate
            System.out.println("Judetele sortate:");
            for (String line : judete) {
                System.out.println(line);
            }

            // Separator intre cele doua parti ale problemei, pentru un aspect mai frumos :)
            System.out.println("-------------------------------------------------");

            // Cautare judet
            System.out.println("Ce judet doriti sa cautati?");
            Scanner scanner = new Scanner(System.in);
            String judet = scanner.next();
            int pozitie = Arrays.binarySearch(judete, judet);
            if (pozitie < 0) System.out.println("Judetul cautat nu exista.");
            else System.out.println("Judetul cautat se afla la pozitia " + (pozitie + 1));

        } catch (IOException e) {
            System.out.println("Eroare la citirea fișierului: " + e.getMessage());
        }
    }
}
