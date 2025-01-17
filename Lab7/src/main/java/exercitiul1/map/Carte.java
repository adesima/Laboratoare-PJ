package exercitiul1.map;

public class Carte {
    private String titlu;
    private String autor;
    private int an;

    public Carte(String titlu, String autor, int an) {
        this.titlu = titlu;
        this.autor = autor;
        this.an = an;
    }

    public String getTitlu() {
        return titlu;
    }

    public void setTitlu(String titlu) {
        this.titlu = titlu;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getAn() {
        return an;
    }

    public void setAn(int an) {
        this.an = an;
    }

    @Override
    public String toString() {
        return "\"" + titlu + "\", de " + autor + ", " + an;
    }
}
