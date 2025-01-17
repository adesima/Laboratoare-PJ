package tema.exercitiul4;

/*4. Să se determine cmmdc a două numere naturale, a căror valoare maximă este 30. Numerele
vor fi generate aleatoriu cu ajutorul unui obiect de tip Random și metodei nextInt();*/

import java.util.Random;

public class MainApp {
    public static void main(String[] args) {
        // generare doua numere random cu valoarea maxima 30
        Random r = new Random();
        int a = r.nextInt(30);
        int b = r.nextInt(30);

        System.out.println("Au fost generate numerele " + a + " si " + b);
        // calcul cmmdc
        while (a != b)
            if (a < b)
                b = b - a;
            else
                a = a - b;
        System.out.println("Cel mai mare divizor comun al acestora este " + a);
    }
}
