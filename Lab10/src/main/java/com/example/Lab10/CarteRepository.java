package com.example.Lab10;

import com.example.demo.model.Carte;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CarteRepository extends JpaRepository<Carte, String> {
    List<Carte> findByAutor(String autor);
}

