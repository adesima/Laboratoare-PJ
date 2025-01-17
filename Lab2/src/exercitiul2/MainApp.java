package exercitiul2;

/*2. Fișierul cantec_in.txt conține versurile unui cântec la alegere. Să se scrie un
program care creează fișierul cantec_out.txt, care conține versurile cântecului original dar în
plus în dreptul fiecărui rând sunt afișate numărul de cuvinte de pe rând şi numărul de vocale
de pe fiecare rând. În dreptul rândurilor care se încheie cu o grupare de litere aleasă se va
pune o steluță (*). Rândurile pentru care un număr generat aleator este mai mic decât 0.1 vor
fi scrise cu majuscule (se vor genera aleator numere între 0 şi 1).
Se va defini o clasă Vers, care are o variabilă membră privată un șir de caractere
reprezentând versul și se va dezvolta câte un operator pentru fiecare cerință de mai sus
(o metodă care returnează numărul de cuvinte, o metodă care returnează numărul de vocale, etc).
Se va crea un vector de obiecte de tip Vers care va conține informația preluată din fișierul de
intrare*/

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MainApp {
    public static void main(String[] args) {
        try {
            // Citesc versurile din fisier si le adaug intr-o lista
            BufferedReader flux_in = new BufferedReader(new InputStreamReader(new FileInputStream("cantec_in.txt")));
            List<Vers> lista = new ArrayList<>();
            PrintStream flux_out = new PrintStream(new FileOutputStream("cantec_out.txt"));

            String data;
            while ((data = flux_in.readLine()) != null) {
                Vers v = new Vers(data);
                lista.add(v);
            }

            // Convertesc lista intr-un vector de Versuri
            Vers[] versuri = lista.toArray(new Vers[0]);

            // Transform fiecare vers folosindu-ma de functiile scrise in clasa Vers
            for (Vers vers : versuri) {
                vers.setVers(vers.steluta(vers.getVers(), "ain") +
                        vers.majuscule(vers.getVers()) + " => " +
                        vers.cuvinte(vers.getVers()) + " cuvinte, " +
                        vers.vocale(vers.getVers()) + " vocale, "
                );
                flux_out.append(vers.getVers() + "\n");
            }

            System.out.println("Versurile au fost rescrise in fisier.");
            flux_out.close();
        }
        catch (IOException e) {
            System.out.println("Eroare la deschiderea fisierului: " + e.getMessage());
        }
    }
}
