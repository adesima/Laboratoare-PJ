package student.group.Lab11;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import student.group.Lab11.EvenimentRepository;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/jpa/evenimente")
public class EvenimenteController {

    @Autowired
    private EvenimentRepository evenimentRepository;

    // Afișează toate evenimentele
    @GetMapping
    public List<Eveniment> getEvenimente() {
        return evenimentRepository.findAll();
    }

    // Afișează evenimentele după locație
    @GetMapping("/locatie/{locatie}")
    public List<Eveniment> getEvenimenteLocatie(@PathVariable String locatie) {
        return evenimentRepository.findByLocatie(locatie);
    }

    // Afișează evenimentele după dată
    @GetMapping("/data/{data}")
    public List<Eveniment> getEvenimenteData(@PathVariable String data) {
        return evenimentRepository.findByData(LocalDate.parse(data));
    }

    // Adăugare eveniment
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Eveniment addEveniment(@RequestBody Eveniment eveniment) {
        return evenimentRepository.save(eveniment);
    }

    // Actualizare eveniment
    @PutMapping
    public Eveniment updateEveniment(@RequestBody Eveniment eveniment) {
        return evenimentRepository.save(eveniment);
    }

    // Ștergere eveniment
    @DeleteMapping("/id/{id}")
    public void deleteEveniment(@PathVariable Long id) {
        evenimentRepository.deleteById(id);
    }
}

