package exercitiu;

public class Persoana {
    private int id;
    private String nume;
    private int varsta;

    // Constructori, getter și setter
    public Persoana(int id, String nume, int varsta) {
        this.id = id;
        this.nume = nume;
        this.varsta = varsta;
    }

    public Persoana(String nume, int varsta) {
        this(0, nume, varsta);
    }

    public int getId() {
        return id;
    }

    public String getNume() {
        return nume;
    }

    public int getVarsta() {
        return varsta;
    }


}
