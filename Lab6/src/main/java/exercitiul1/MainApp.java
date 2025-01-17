package exercitiul1;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class MainApp {
    public static void main(String[] args) {
//        List<Angajat> angajati = Arrays.asList(
//                new Angajat("Ion Popescu", "director", LocalDate.of(2020, Month.JANUARY, 15), 5000),
//                new Angajat("Maria Ionescu", "sef echipa", LocalDate.of(2021, Month.APRIL, 10), 4000),
//                new Angajat("Vasile Gheorghe", "analist", LocalDate.of(2022, Month.JULY, 5), 2500),
//                new Angajat("Andrei Vasilescu", "programator", LocalDate.of(2023, Month.AUGUST, 12), 3000)
//        );
        List<Angajat> angajati = citire();

        // 1. afisarea listei de angajati folosind referinte la metode
        System.out.println("Lista angajatilor:");
        angajati.forEach(System.out::println);

        // 2. afisarea angajatilor care au salariul peste 2500 RON
        System.out.println("\nAngajatii cu salariul peste 2500 RON:");
        angajati.stream().filter(ang -> ang.getSalariul() > 2500).forEach(System.out::println);

        // 3. crearea unei liste cu angajatii din aprilie anul trecut cu functii de conducere
        int anulCurent = LocalDate.now().getYear();
        List<Angajat> angajatiAprilie = angajati.stream().filter(ang -> ang.getData_angajarii().getMonth() == Month.APRIL &&
                ang.getData_angajarii().getYear() == anulCurent - 1 && (ang.getPostul().contains("sef") || ang.getPostul().contains("director"))).collect(Collectors.toList());
        //collectors.tolist -> transforma rezultatul unui flux de date (Stream) intr-o colectie de tip List.

        System.out.println("\nAngajatii din aprilie anul trecut cu functii de conducere:");
        angajatiAprilie.forEach(System.out::println);

        // 4. afisarea angajatilor fara functii de conducere in ordine descrescatoare a salariilor
        System.out.println("\nAngajatii fara functii de conducere in ordine descrescatoare a salariilor:");
        angajati.stream().filter(ang -> !ang.getPostul().contains("sef") && !ang.getPostul().contains("director")).sorted((a1, a2) -> Float.compare(a2.getSalariul(), a1.getSalariul())).forEach(System.out::println);

        // 5. extragerea numelor angajaților scrise cu majuscule
        System.out.println("\nNumele angajatilor scrise cu majuscule:");
        List<String> numeAngajati = angajati.stream().map(ang -> ang.getNume().toUpperCase()).collect(Collectors.toList());
        numeAngajati.forEach(System.out::println);

        // 6. afisarea salariilor < de 3000 RON
        System.out.println("\nSalariile mai mici de 3000 RON:");
        angajati.stream().map(Angajat::getSalariul).filter(sal -> sal < 3000).forEach(System.out::println);

        // 7. afisarea datelor primului angajat al firmei
        System.out.println("\nPrimul angajat al firmei:");
        angajati.stream().min(Comparator.comparing(Angajat::getData_angajarii)).ifPresentOrElse(
                System.out::println, () -> System.out.println("Nu exista angajati.")
        );

        // 8. afisarea de statistici referitoare la salariu
        System.out.println("\nStatistici despre salarii:");
        var stats = angajati.stream().collect(Collectors.summarizingDouble(Angajat::getSalariul));
        System.out.println("Salariul mediu: " + stats.getAverage());
        System.out.println("Salariul minim: " + stats.getMin());
        System.out.println("Salariul maxim: " + stats.getMax());

        // 9. verificarea existentei unui angajat numit Ion
        System.out.println("\nExistenta unui angajat numit Ion:");
        angajati.stream().map(Angajat::getNume).filter(nume -> nume.startsWith("Ion")).findAny().ifPresentOrElse(
                name -> System.out.println("Firma are cel puțin un Ion angajat."),
                () -> System.out.println("Firma nu are nici un Ion angajat.")
        );

        // 10. numarul de persoane angajate vara anului precedent
        System.out.println("\nNumarul de persoane angajate vara anului precedent:");
        long angajatiVara = angajati.stream().filter(ang -> {
            LocalDate data = ang.getData_angajarii();
            int an = anulCurent - 1;
            return data.getYear() == an &&
                    (data.getMonth() == Month.JUNE || data.getMonth() == Month.JULY || data.getMonth() == Month.AUGUST);
        }).count();
        System.out.println("Angajati vara anului trecut: " + angajatiVara);
    }

    //    public static void scriere(List<Angajat> lista) {
//        try {
//            ObjectMapper mapper=new ObjectMapper();
//            mapper.registerModule(new JavaTimeModule());
//            File file=new File("src/main/resources/angajati.json");
//            mapper.writeValue(file,lista);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
    public static List<Angajat> citire() {
        try {
            File file = new File("src/main/resources/angajati.json");
            ObjectMapper mapper = new ObjectMapper();
            // Înregistrează modulul pentru gestionarea LocalDate
            mapper.registerModule(new JavaTimeModule());
            List<Angajat> angajati = mapper.readValue(file, new TypeReference<List<Angajat>>() {
            });
            return angajati;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
