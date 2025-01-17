package exercitiul2;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class MainApp {
    public static void scriere(List<PerecheNumere> lista) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writeValue(new File("perechi.json"), lista);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<PerecheNumere> citire() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(new File("perechi.json"), new TypeReference<List<PerecheNumere>>() {});
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        List<PerecheNumere> lista = List.of(
                new PerecheNumere(8, 13),
                new PerecheNumere(21, 34),
                new PerecheNumere(5, 6)
        );

        scriere(lista);
        List<PerecheNumere> listaCitita = citire();

        if (listaCitita != null) {
            listaCitita.forEach(System.out::println);
        }
    }
}
