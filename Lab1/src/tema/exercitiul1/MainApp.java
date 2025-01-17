package tema.exercitiul1;

/*1. Se cere să se scrie un program Java care să calculeze şi să afişeze perimetru şi aria unui
dreptunghi. Valorile pentru lungime şi lățime se citesc de la tastatura. Sa se adauge un break
point pe prima linie care citește valoarea unei laturi si din acel punct să se ruleze programul
linie cu linie urmărind valorile variabilelor în memorie.*/

import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) {
        // citire lungime si latime
        Scanner scanner = new Scanner(System.in);
        System.out.print("latimea: ");
        int latime = scanner.nextInt();
        System.out.print("lungimea: ");
        int lungime = scanner.nextInt();

        // calcul arie si perimetru
        int arie = latime * lungime;
        int perimetru = 2 * latime + 2 * lungime;

        // afisare arie si perimetru
        System.out.println("Perimetrul este " + perimetru + " cm.");
        System.out.println("Aria este " + arie + " cm.");
    }
}
