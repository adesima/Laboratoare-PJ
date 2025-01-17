package exercitiul2;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.BasicPolymorphicTypeValidator;
import com.fasterxml.jackson.databind.jsontype.PolymorphicTypeValidator;

import java.io.*;
import java.sql.Array;
import java.util.*;

public class MainApp {
    private static final String FILE_PATH = "src/main/resources/instrumente.json";

    public static void main(String[] args) throws IOException {
        // crearea colectiei de instrumente
        Set<InstrumentMuzical> instrumente = new HashSet<>();
        instrumente.add(new Chitara("Yamaha", 2500, TipChitara.Acustica, 6));
        instrumente.add(new Chitara("Fender", 3500, TipChitara.Electrica, 7));
        instrumente.add(new Chitara("Ibanez", 3000, TipChitara.Clasica, 6));
        instrumente.add(new SetTobe("Roland", 5000, TipTobe.Electronice, 5, 3));
        instrumente.add(new SetTobe("Pearl", 4000, TipTobe.Acustice, 6, 2));
        instrumente.add(new SetTobe("Yamaha", 3000, TipTobe.Acustice, 4, 1));

        // salvarea initiala a colectiei
        salveazaFisier(instrumente);

        // citirea colectiei din fisier
        Set<InstrumentMuzical> instrumenteCitite = citesteFisier();

        Scanner scanner = new Scanner(System.in);
        int opt;

        do {
            // afisare meniu
            System.out.println("\n Meniu");
            System.out.println("1. Afiseaza colectia citita din fisier");
            System.out.println("2. Verifica duplicate in colectia Set");
            System.out.println("3. Sterge instrumentele cu preț > 3000 RON");
            System.out.println("4. Afiseaza toate datele chitarilor");
            System.out.println("5. Afiseaza toate datele tobelor");
            System.out.println("6. Afiseaza chitara cu cele mai multe corzi");
            System.out.println("7. Afiseaza tobele acustice ordonate dupa numarul de tobe");
            System.out.println("8. Iesire");
            System.out.print("Introduceti optiunea dvs: ");

            opt = scanner.nextInt();
            scanner.nextLine(); // consuma newline-ul ramas

            switch (opt) {
                case 1:
                    System.out.println("\nColectia citita din fisier:");
                    instrumenteCitite.forEach(System.out::println);
                    break;

                case 2:
                    Chitara chitaraDuplicata = new Chitara("Yamaha", 2500, TipChitara.Acustica, 6);
                    if (!instrumenteCitite.add(chitaraDuplicata)) {
                        System.out.println("\nDuplicatele nu sunt permise in colectia Set.");
                    }
                    break;

                case 3:
                    instrumenteCitite.removeIf(instrument -> instrument.getPret() > 3000);
                    System.out.println("\nColectia dupa stergerea instrumentelor scumpe:");
                    instrumenteCitite.forEach(System.out::println);
                    break;

                case 4:
                    System.out.println("\nDatele chitarilor:");
                    instrumenteCitite.stream().filter(instrument -> instrument instanceof Chitara).forEach(System.out::println);
                    break;

                case 5:
                    System.out.println("\nDatele tobelor:");
                    instrumenteCitite.stream().filter(instrument -> instrument.getClass() == SetTobe.class).forEach(System.out::println);
                    break;

                case 6:
                    instrumenteCitite.stream().filter(instrument -> instrument instanceof Chitara).map(instrument -> (Chitara) instrument)
                            .max(Comparator.comparingInt(Chitara::getNrCorzi)).ifPresent(chitara -> System.out.println("\nChitara cu cele mai multe corzi: " + chitara));
                    break;

                case 7:
                    System.out.println("\nTobele acustice ordonate dupa numarul de tobe:");
                    instrumenteCitite.stream().filter(instrument -> instrument instanceof SetTobe && ((SetTobe) instrument).getTipTobe() == TipTobe.Acustice)
                            .map(instrument -> (SetTobe) instrument).sorted(Comparator.comparingInt(SetTobe::getNrTobe)).forEach(System.out::println);
                    break;

                case 8:
                    System.out.println("\nzi buna");
                    break;

                default:
                    System.out.println("\nOptiune invalida! Alege o optiune valida.");
            }

        } while (opt != 8);

        scanner.close();
    }

    private static void salveazaFisier(Set<InstrumentMuzical> instrumente) throws IOException {
        PolymorphicTypeValidator ptv = BasicPolymorphicTypeValidator.builder().allowIfSubType("com.baeldung.jackson.inheritance").allowIfSubType("java.util.ArrayList").build();
        ObjectMapper mapper = new ObjectMapper();
        mapper.activateDefaultTyping(ptv, ObjectMapper.DefaultTyping.NON_FINAL);
        mapper.writeValue(new File(FILE_PATH), instrumente);
        System.out.println("\nColectia a fost salvata in fisier.");
    }

    private static Set<InstrumentMuzical> citesteFisier() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.activateDefaultTyping(mapper.getPolymorphicTypeValidator(), ObjectMapper.DefaultTyping.NON_FINAL);
        return mapper.readValue(new File(FILE_PATH), new TypeReference<Set<InstrumentMuzical>>() {});
    }
}
