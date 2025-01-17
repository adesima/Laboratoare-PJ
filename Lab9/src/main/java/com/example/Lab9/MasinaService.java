package com.example.Lab9;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MasinaService {

    @Autowired
    private MasinaRepository masinaRepository;

    public void adaugaMasina(Masina masina) {
        masinaRepository.save(masina);
    }

    public void stergeMasina(String nrInmatriculare) {
        Masina masina = masinaRepository.findByNrInmatriculare(nrInmatriculare);
        if (masina != null) {
            masinaRepository.delete(masina);
        }
    }

    public Masina cautaMasina(String nrInmatriculare) {
        return masinaRepository.findByNrInmatriculare(nrInmatriculare);
    }

    public List<Masina> extrageToateMasinile() {
        return (List<Masina>) masinaRepository.findAll();
    }

    public int numarMasiniCuMarca(String marca) {
        return masinaRepository.countByMarca(marca);
    }

    public int numarMasiniSub100000Km() {
        return masinaRepository.countMasiniSub100000Km();
    }

    public List<Masina> masiniMaiNoiDe5Ani() {
        return masinaRepository.findMasiniMaiNoiDe5Ani(2018);
    }
}
