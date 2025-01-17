package com.example.Lab9;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/masini")
public class MasinaController {

    @Autowired
    private MasinaService masinaService;

    @PostMapping("/adauga")
    public void adaugaMasina(@RequestBody Masina masina) {
        masinaService.adaugaMasina(masina);
    }

    @DeleteMapping("/sterge/{nrInmatriculare}")
    public void stergeMasina(@PathVariable String nrInmatriculare) {
        masinaService.stergeMasina(nrInmatriculare);
    }

    @GetMapping("/{nrInmatriculare}")
    public Masina cautaMasina(@PathVariable String nrInmatriculare) {
        return masinaService.cautaMasina(nrInmatriculare);
    }

    @GetMapping("/toate")
    public List<Masina> extrageToateMasinile() {
        return masinaService.extrageToateMasinile();
    }

    @GetMapping("/count-marca")
    public int numarMasiniCuMarca(@RequestParam String marca) {
        return masinaService.numarMasiniCuMarca(marca);
    }

    @GetMapping("/count-km")
    public int numarMasiniSub100000Km() {
        return masinaService.numarMasiniSub100000Km();
    }

    @GetMapping("/masini-mai-noi-de-5-ani")
    public List<Masina> masiniMaiNoiDe5Ani() {
        return masinaService.masiniMaiNoiDe5Ani();
    }
}

