package exercitiul3;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import java.util.*;
import java.io.*;

public class MainApp {
    public static List<Mobilier> citireMobilier(String fisier) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(new File(fisier), new TypeReference<List<Mobilier>>() {});
        } catch (IOException e) {
            //printStackTrace afis. detalii despre exceptie, unde si de ce a aparut in timpul executiei programului
            e.printStackTrace();
            return null;
        }
    }

    public static void afisareMobilier(List<Mobilier> listaMobilier) {
        for (Mobilier mobilier : listaMobilier) {
            System.out.println("Mobilier: " + mobilier.getNume());
            for (Placa placa : mobilier.getPlaci()) {
                System.out.println("\t" + placa);
            }
        }
    }

    public static void afisareCaracteristici(List<Mobilier> listaMobilier, String numeMobilier) {
        for (Mobilier mobilier : listaMobilier) {
            if (mobilier.getNume().equalsIgnoreCase(numeMobilier)) {
                System.out.println("Placile pentru mobilierul: " + numeMobilier);
                for (Placa placa : mobilier.getPlaci()) {
                    System.out.println(placa);
                }
                return;
            }
        }
        System.out.println("Mobilierul " + numeMobilier + " nu a fost gasit.");
    }

    public static int numarColiPal(List<Mobilier> listaMobilier, String numeMobilier) {
        final int ARIE_COLA_PAL = 2800 * 2070;
        int arieTotala = 0;

        for (Mobilier mobilier : listaMobilier) {
            if (mobilier.getNume().equalsIgnoreCase(numeMobilier)) {
                for (Placa placa : mobilier.getPlaci()) {
                    arieTotala += placa.getLungime() * placa.getLatime() * placa.getNrBucati();
                }
                return (int) Math.ceil((double) arieTotala / ARIE_COLA_PAL);
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        // citirea din JSON
        List<Mobilier> listaMobilier = citireMobilier("mobilier.json");

        // afisarea listei de mobilier
        if (listaMobilier != null) {
            afisareMobilier(listaMobilier);

            // afisarea placilor pentru un mobilier specific
            afisareCaracteristici(listaMobilier, "dulap");

            // calcularea numarului de coli necesare
            int coliNecesare = numarColiPal(listaMobilier, "dulap");
            System.out.println("Numar aprox. de coli necesare: " + coliNecesare);
        }
    }
}
