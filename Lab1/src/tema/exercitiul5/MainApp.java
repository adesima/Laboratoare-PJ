package tema.exercitiul5;

/*5. Să se scrie un program care generează aleatoriu un număr întreg cuprins între 0 și 20.
Programul va determina dacă numărul aparține șirului lui Fobonacci.*/

import java.util.Random;

public class MainApp {
    public static void main(String[] args) {
        // generare numar random
        Random r = new Random();
        int n = r.nextInt(20);  // cuprins intre 0 si 20

        // determinare daca numarul apartine sirului
        switch (n) {
            // numerele din sirul lui Fibonacci cuprinse intre 0 si 20
            case 0:
            case 1:
            case 2:
            case 3:
            case 5:
            case 8:
            case 13:
                System.out.println("Numarul " + n + " apartine sirului lui Fibonacci.");
                break;
            default:
                System.out.println("Numarul " + n + " nu apartine sirului lui Fibonacci.");
                break;
        }
    }
}