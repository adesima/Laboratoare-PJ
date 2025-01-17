package exercitiul1.map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class MainApp {
    private static final String FILE_PATH = "src/main/resources/carti.json";
    private static Map<Integer, Carte> carti = new HashMap<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        citesteFisier();

        int opt;
        while(true) {
            meniu();
            opt = scanner.nextInt();
            switch (opt) {
                case 1:
                    afisColectia();
                    break;
                case 2:
                    stergereCarte();
                    break;
                case 3:
                    adaugCarte();
                    break;
                case 4:
                    salveazaFisier();
                    break;
                case 5:
                    filtreazaAutor("Yuval Noah Harari");
                    break;
                case 6:
                    sorteazaTitlu();
                    break;
                case 7:
                    afisVeche();
                    break;
                default:System.out.println("Optiune invalida! Introduceti alta optiune.");
                    break;
            }
        }
    }

    // afisarea meniului
    private static void meniu() {
        System.out.println("\nMeniu");
        System.out.println("1. Afiseaza colectia de carti");
        System.out.println("2. Sterge o carte din colectie");
        System.out.println("3. Adauga o carte la colectie");
        System.out.println("4. Salveaza colectia modificata in fisier");
        System.out.println("5. Afiseaza carțile autorului Yuval Noah Harari");
        System.out.println("6. Afiseaza cartile ordonate dupa titlu");
        System.out.println("7. Afiseaza cea mai veche carte");
        System.out.print("Introduceti optiunea dvs: ");
    }

    // citirea fisierului Json
    private static void citesteFisier() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Map<String, Object>> data = mapper.readValue(new File(FILE_PATH),
                new TypeReference<>() {
                });
        for (Map.Entry<String, Map<String, Object>> entry : data.entrySet()) {
            int id = Integer.parseInt(entry.getKey());
            Map<String, Object> carteInfo = entry.getValue();
            String titlul = (String) carteInfo.get("titlul");
            String autorul = (String) carteInfo.get("autorul");
            int anul = (int) carteInfo.get("anul");
            carti.put(id, new Carte(titlul, autorul, anul));
        }
    }

    // afisarea colectiei
    private static void afisColectia() {
        System.out.println("\nColectia de carti:");
        carti.forEach((id, carte) -> System.out.println("ID: " + id + ", " + carte));
    }

    // stergerea unei carti
    private static void stergereCarte() {
        System.out.print("\nIntrodu ID-ul cartii de sters: ");
        int id = scanner.nextInt();
        if (carti.remove(id) != null) {
            System.out.println("Cartea cu ID-ul " + id + " a fost stearsa.");
        } else {
            System.out.println("ID-ul introdus nu există.");
        }
    }

    // adaugarea unei carti noi
    private static void adaugCarte() {
        System.out.print("\nIntrodu ID-ul noii carti: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Introdu titlul cartii: ");
        String titlul = scanner.nextLine();
        System.out.print("Introdu autorul cartii: ");
        String autorul = scanner.nextLine();
        System.out.print("Introdu anul aparitiei: ");
        int anul = Integer.parseInt(scanner.nextLine());

        Carte newCarte = new Carte(titlul, autorul, anul);
        if (carti.putIfAbsent(id, newCarte) == null) {
            System.out.println("Cartea a fost adaugata cu succes.");
        } else {
            System.out.println("ID-ul exista deja. Cartea nu a fost adaugata.");
        }
    }
    // salvarea colectiei in fisier
    private static void salveazaFisier() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            Map<String, Object> outputData = new HashMap<>();
            for (Map.Entry<Integer, Carte> entry : carti.entrySet()) {
                Map<String, Object> carteInfo = new HashMap<>();
                carteInfo.put("titlul", entry.getValue().getTitlu());
                carteInfo.put("autorul", entry.getValue().getAutor());
                carteInfo.put("anul", entry.getValue().getAn());
                outputData.put(String.valueOf(entry.getKey()), carteInfo);
            }
            mapper.writeValue(new File(FILE_PATH), outputData);
            System.out.println("Colectia a fost salvata in fisier.");
        } catch (IOException e) {
            System.err.println("Eroare la salvarea fisierului: " + e.getMessage());
        }
    }

    // filtrarea cartilor lui Yuval Noah Harari
    private static void filtreazaAutor(String autor) {
        System.out.println("\nCartile lui " + autor + ":");
        Set<Carte> cartiAutor = carti.values().stream().filter(carte -> carte.getAutor().equals(autor)).collect(Collectors.toSet());
        cartiAutor.forEach(System.out::println);
    }

    // ordonarea cartilor dupa titlu
    private static void sorteazaTitlu() {
        System.out.println("\nCartile ordonate dupa titlu:");
        carti.values().stream().sorted(Comparator.comparing(Carte::getTitlu)).forEach(System.out::println);
    }

    // afisarea celei mai vechi carti
    private static void afisVeche() {
        Optional<Carte> ceaMaiVecheCarte = carti.values().stream().min(Comparator.comparingInt(Carte::getAn));
        ceaMaiVecheCarte.ifPresentOrElse(carte -> System.out.println("\nCea mai veche carte: " + carte), () -> System.out.println("Colecția este goală.")
        );
    }
}
