package Tema.problema1;

import static java.lang.Math.*;

public class Parabola {
    // coeficienti
    private int a;
    private int b;
    private int c;

    // constructor cu 3 parametri
    public Parabola(int a, int b, int c){
        super();
        this.a = a;
        this.b = b;
        this.c = c;
    }

    // metoda care calculeaza varful parabolei
    public static Punct Varf(int a, int b, int c) {
        double x, y;
        x = -b/2*a;
        y = (-b*b + 4*a*c)/4*a;
        Punct v = new Punct(x, y);
        return v;
    }

    @Override
    public String toString() {
        return "f(x) = " + a + "x^2 + " + b + "x + " + c;
    }

    // metoda care returneaza coordonatele mijloculio segmentului care uneste varful parabolei cu cea curenta
    public Punct MijlocSegmentCareUnesteVarfurile (Parabola p_in) {
        Punct v_cur = Varf(this.a, this.b, this.c);
        Punct v_in = Varf(p_in.a, p_in.b, p_in.c);
        Punct mijloc = new Punct((v_in.getX() + v_cur.getX())/2, (v_in.getY() + v_cur.getY())/2);
        return mijloc;
    }

    // metoda care returneaza coordonatele mijlocului segmentului care uneste varfurile a doua parabole
    public static Punct MijlocSegmentCareUnesteVarfurile (Parabola p1, Parabola p2) {
        Punct v1 = Varf(p1.a, p1.b, p1.c);
        Punct v2 = Varf(p2.a, p2.b, p2.c);
        Punct mijloc = new Punct((v1.getX() + v2.getX())/2, (v1.getY() + v2.getY())/2);
        return mijloc;
    }

    // metoda care returneaza lungimea segmentului care uneste varfuril parabolei cu cea curenta
    public double LungimeSegmentCareUnesteVarfurile (Parabola p_in) {
        Punct v_in = Varf(p_in.a, p_in.b, p_in.c);
        Punct v_cur = Varf(this.a, this.b, this.c);
        return hypot(v_cur.getX() - v_in.getX(), v_cur.getY() - v_in.getY());
    }

    // metoda care returneaza lungimea segmentului care uneste varfurile a doua parabole
    public static double LungimeSegmentCareUnesteVarfurile (Parabola p1, Parabola p2) {
        Punct v1 = Varf(p1.a, p1.b, p1.c);
        Punct v2 = Varf(p2.a, p2.b, p2.c);
        return hypot(v2.getX() - v1.getX(), v2.getY() - v1.getY());
    }
}
