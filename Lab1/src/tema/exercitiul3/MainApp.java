package tema.exercitiul3;

/*3. Să se scrie un program care citește un număr n natural de la tastatură și afișează toți
divizorii acestuia pe ecran. Dacă numărul este prim se va afișa un mesaj corespunzător.*/

import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) {
        // citire n
        System.out.print("n = ");
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        System.out.println("Divizorii numarului " + n + " sunt:");
        System.out.println("1");
        // determinare divizori
        int nr_div = 0;
        for (int i = 2; i <= n/2; i++)
            if (n % i == 0) {
                nr_div++;   // numarare divizori pentru a determina daca numarul este prim
                System.out.println(i);
            }
        System.out.println(n);

        if (nr_div == 0)
            System.out.println("Numarul " + n + " este prim.");
    }
}
