package student.group.Lab11;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class Eveniment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String denumire;
    private String locatie;
    private LocalDate data;
    private LocalTime timp;
    private float pretBilet;

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDenumire() {
        return denumire;
    }

    public void setDenumire(String denumire) {
        this.denumire = denumire;
    }

    public String getLocatie() {
        return locatie;
    }

    public void setLocatie(String locatie) {
        this.locatie = locatie;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public LocalTime getTimp() {
        return timp;
    }

    public void setTimp(LocalTime timp) {
        this.timp = timp;
    }

    public float getPretBilet() {
        return pretBilet;
    }

    public void setPretBilet(float pretBilet) {
        this.pretBilet = pretBilet;
    }
}

