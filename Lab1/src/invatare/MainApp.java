package invatare;

import java.io.*;
import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) throws FileNotFoundException {

        // ----------Citirea unei valori de la tastatura si afisarea acesteia
        /*Scanner scanner = new Scanner(System.in);
        System.out.print("a = ");
        int a = scanner.nextInt();
        System.out.println("Ati introdus valoarea " + a);
        scanner.close();*/

        // ---------- Operatii de citire a liniilor de text
        /*String nume_fis = "in.txt";
        BufferedReader flux_in;
        flux_in = new BufferedReader(new InputStreamReader(new FileInputStream(nume_fis)));*/
        //      sau
        /*flux_in = new BufferedReader(new FileReader(nume_fis));*/
        //      Daca citirea se face de la tastatura
        /*BufferedReader flux_in = new BufferedReader(new InputStreamReader(System.in));
        linie = flux_in.readLine();*/      // - linie e de tipul String

        // int x = Integer.parseInt("123");

        // ---------- Operatii de citire a tipurilor primitive
        //      Pentru citirea de la tastatura
        /*Scanner scanner = new Scanner(System.in);
        int x = scanner.nextInt();*/
        //      Pentru citirea din fisier
        /*Scanner scanner = new Scanner(new File("in.txt"));*/

        // ---------- Operatii de scriere a liniilor de text
        /*PrintStream flux_out = new PrintStream(nume_fis);*/
    }
}
