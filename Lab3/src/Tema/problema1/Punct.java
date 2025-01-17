package Tema.problema1;

class Punct {
    private final double x;
    private final double y;
    public Punct(double x, double y) {
        super();
        this.x = x;
        this.y = y;
    }
    @Override
    public String toString() {
        return "(" + x + "," + y + ")";
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}
