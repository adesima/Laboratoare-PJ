package exercitiul1;

enum ModulTiparire {color, alb_negru};

public class Imprimata extends Echipament {
    private int ppm;
    private String rezoltie;
    private int p_car;
    private ModulTiparire mod;

    public Imprimata(String denumire, int nr_inv, double pret, String zona_mag, Situatie situatie, String tip, int ppm, String rezoltie, int p_car, ModulTiparire mod) {
        super(denumire, nr_inv, pret, zona_mag, situatie, tip);
        this.ppm = ppm;
        this.rezoltie = rezoltie;
        this.p_car = p_car;
        this.mod = mod;
    }

    public String getRezoltie() {
        return rezoltie;
    }

    public void setRezoltie(String rezoltie) {
        this.rezoltie = rezoltie;
    }

    public int getPpm() {
        return ppm;
    }

    public void setPpm(int ppm) {
        this.ppm = ppm;
    }

    public int getP_car() {
        return p_car;
    }

    public void setP_car(int p_car) {
        this.p_car = p_car;
    }

    public ModulTiparire getMod() {
        return mod;
    }

    public void setMod(ModulTiparire mod) {
        this.mod = mod;
    }

    @Override
    public String toString() {
        return super.toString() + ", " +
                ppm + " ppm, " +
                rezoltie + ", " +
                p_car + " p/cart, " +
                mod;
    }
}
