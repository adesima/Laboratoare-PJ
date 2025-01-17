package com.example.Lab10;


import com.example.demo.model.Carte;
import com.example.demo.repository.CarteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/carti")
public class CarteWebController {

    @Autowired
    private CarteRepository carteRepository;

    @GetMapping("/lista-carti")
    public String getCarti(Model model) {
        List<Carte> carti = carteRepository.findAll();
        model.addAttribute("carti", carti);
        return "carti";
    }

    @PostMapping("/adauga")
    public String adaugaCarte(@RequestParam String isbn, @RequestParam String titlu, @RequestParam String autor, Model model) {
        if (isbn.isEmpty() || titlu.isEmpty() || autor.isEmpty()) {
            model.addAttribute("message", "Adaugarea nu se realizează dacă nu completaţi toate caracteristicile!");
        } else {
            Carte carte = new Carte(isbn, titlu, autor);
            carteRepository.save(carte);
            model.addAttribute("message", "Adaugare realizata cu succes!");
        }
        return "carti";
    }

    @PostMapping("/sterge")
    public String stergeCarte(@RequestParam String isbn, Model model) {
        if (carteRepository.existsById(isbn)) {
            carteRepository.deleteById(isbn);
            model.addAttribute("message", "Cartea a fost stearsa!");
        } else {
            model.addAttribute("message", "Nu se gaseste nici o carte cu isbn-ul introdus.");
        }
        return "carti";
    }

    @PostMapping("/modifica")
    public String modificaCarte(@RequestParam String isbn, @RequestParam String titlu, @RequestParam String autor, Model model) {
        if (carteRepository.existsById(isbn)) {
            Carte carte = carteRepository.findById(isbn).orElse(null);
            assert carte != null;
            carte.setTitlu(titlu);
            carte.setAutor(autor);
            carteRepository.save(carte);
            model.addAttribute("message", "Cartea cu ISBN-ul " + isbn + " a fost modificata!");
        } else {
            model.addAttribute("message", "Nu se gaseste nici o carte cu isbn-ul introdus.");
        }
        return "carti";
    }

    @PostMapping("/filtrare")
    public String filtreazaCarti(@RequestParam String autor, Model model) {
        if (autor.isEmpty()) {
            List<Carte> carti = carteRepository.findAll();
            model.addAttribute("message", "Toate cărțile:");
            model.addAttribute("carti", carti);
        } else {
            List<Carte> carti = carteRepository.findByAutor(autor);
            if (carti.isEmpty()) {
                model.addAttribute("message", "Nu există cărți ale autorului " + autor);
            } else {
                model.addAttribute("message", "Cărțile următoare aparțin autorului " + autor);
                model.addAttribute("carti", carti);
            }
        }
        return "carti";
    }
}
