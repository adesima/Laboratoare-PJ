package exercitiu;

public class Excursie {
    private int idExcursie;
    private int idPersoana;
    private String destinatia;
    private int anul;

    // Constructori, getter și setter
    public Excursie(int idExcursie, int idPersoana, String destinatia, int anul) {
        this.idExcursie = idExcursie;
        this.idPersoana = idPersoana;
        this.destinatia = destinatia;
        this.anul = anul;
    }

    public Excursie(int idPersoana, String destinatia, int anul) {
        this(0, idPersoana, destinatia, anul);
    }

    public int getIdExcursie() {
        return idExcursie;
    }

    public int getIdPersoana() {
        return idPersoana;
    }

    public String getDestinatia() {
        return destinatia;
    }

    public int getAnul() {
        return anul;
    }
}
