package exercitiul1;

enum OS {Windows, Linux};

public class SistemCalcul extends Echipament {
    private String tip_mon;
    private double vit_proc;
    private int c_hdd;
    private OS os;

    public SistemCalcul(String denumire, int nr_inv, double pret, String zona_mag, Situatie situatie, String tip, String tip_mon, double vit_proc, int c_hdd, OS os) {
        super(denumire, nr_inv, pret, zona_mag, situatie, tip);
        this.tip_mon = tip_mon;
        this.vit_proc = vit_proc;
        this.c_hdd = c_hdd;
        this.os = os;
    }

    public String getTip_mon() {
        return tip_mon;
    }

    public void setTip_mon(String tip_mon) {
        this.tip_mon = tip_mon;
    }

    public double getVit_proc() {
        return vit_proc;
    }

    public void setVit_proc(int vit_proc) {
        this.vit_proc = vit_proc;
    }

    public int getC_hdd() {
        return c_hdd;
    }

    public void setC_hdd(int c_hdd) {
        this.c_hdd = c_hdd;
    }

    public OS getOs() {
        return os;
    }

    public void setOs(OS os) {
        this.os = os;
    }

    @Override
    public String toString() {
        return super.toString() + ", " +
                "monitor " + tip_mon + ", " +
                vit_proc + " GHz, " +
                c_hdd + " MB, " +
                "OS " + os;
    }
}
