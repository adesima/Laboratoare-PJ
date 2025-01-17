package tema.exercitiul2;

/*2. Să se scrie un program care citește un set de numerele din fișierul de intrare in2.txt, care
conține câte un număr pe un rând, având valorile din figura 18. Programul va determină suma
lor, media aritmetică, valoarea minimă, valoarea maximă, va afișa aceste valori pe ecran și le
va scrie în fișierul de ieșire out2.txt. Media aritmetică va fi afișată ca un număr real.*/

import java.io.*;

public class MainApp {
    public static void main(String[] args) throws FileNotFoundException {
        BufferedReader flux_in = new BufferedReader(new InputStreamReader(new FileInputStream("in2.txt")));
        int sum = 0;
        int nr = 0;
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        PrintStream flux_out = new PrintStream("out2.txt");

        try {
            String data;
            while ((data = flux_in.readLine()) != null) {
                // afisare
                System.out.println(data);

                // calcul suma si numarare pentru medie
                sum += Integer.parseInt(data);
                nr++;

                // determinare minim si maxim
                if (min > Integer.parseInt(data))
                    min = Integer.parseInt(data);
                if (max < Integer.parseInt(data))
                    max = Integer.parseInt(data);

                // scriere fisier
                flux_out.println(data);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        flux_out.close();

        // calcul medie aritmetica
        float medie = (float)sum/nr;

        // afisari
        System.out.println("Suma: " + sum);
        System.out.println("Media aritmetica: " + medie);
        System.out.println("Minim: " + min);
        System.out.println("Maxim: " + max);
    }
}