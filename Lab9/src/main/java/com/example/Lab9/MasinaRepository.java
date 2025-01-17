package com.example.Lab9;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MasinaRepository extends CrudRepository<Masina, Integer> {

    Masina findByNrInmatriculare(String nrInmatriculare);

    @Query("SELECT COUNT(*) FROM masini WHERE marca = :marca")
    int countByMarca(String marca);

    @Query("SELECT COUNT(*) FROM masini WHERE km < 100000")
    int countMasiniSub100000Km();

    @Query("SELECT * FROM masini WHERE an_fabricatie > :anFabricatie")
    List<Masina> findMasiniMaiNoiDe5Ani(Integer anFabricatie);
}
