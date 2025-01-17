package exercitiul3;

/*3. Să se insereze într-o anumită poziție a unui șir de caractere, un alt șir. Datele vor fi
preluate de la tastatură sau din fișier. Să se șteargă o porțiune a unui șir de caractere care
începe dintr-o anumită poziție și are un anumit număr de caractere. Se recomandă utilizarea
clasei StringBuilder.*/

import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) {
        StringBuilder sir_initial;
        String sir_introdus;
        int pozitie;

        // citire sirul initial
        System.out.print("Introduceti un sir de caractere: ");
        Scanner scanner = new Scanner(System.in);
        sir_initial = new StringBuilder(scanner.nextLine());

        // ---------- Inserare ----------
        System.out.println(" ---- Inserare ---- ");
        // citire sirul de introdus
        System.out.println("Ce sir doriti sa iserati?");
        sir_introdus = scanner.nextLine();
        // citire pozitie
        System.out.println("La ce pozitie?");
        pozitie = scanner.nextInt();
        // inserare sir la pozitia aleasa (daca e posibil)
        try {
            sir_initial.insert(pozitie, sir_introdus);
            System.out.println("Sirul nou este: " + sir_initial);
        }
        catch (Exception e) {
            System.out.println("Sirul nu are atatea caractere. Introduceti un numar de la 0 la " + sir_initial.length() + "\n" + e.getMessage());
        }

        // ---------- Stergere ----------
        System.out.println(" ---- Stergere ---- ");
        // citire pozitie
        System.out.println("De la ce pozitie doriti sa inceapa stergerea?");
        pozitie = scanner.nextInt();
        // citire nr de caractere de sters
        System.out.println("Cate caractere doriti sa fie sterse?");
        int nr_caractere = scanner.nextInt();
        // se efectueaza stergerea (daca e posibil)
        try {
            sir_initial.delete(pozitie, pozitie + nr_caractere);
            System.out.println("Sirul nou este: " + sir_initial);
        }
        catch (Exception e) {
            System.out.println("Valorile introduse depasesc lungimea sirului.");
        }

        scanner.close();
    }
}
