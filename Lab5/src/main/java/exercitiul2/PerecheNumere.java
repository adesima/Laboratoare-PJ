package exercitiul2;

import java.util.ArrayList;
import java.util.List;

public class PerecheNumere {
    private int nr1;
    private int nr2;
    //constructor gol
    public PerecheNumere(){}

    public PerecheNumere(int nr1, int nr2){
        this.nr1=nr1;
        this.nr2=nr2;
    }

    public int getNr1() {
        return nr1;
    }

    public void setNr1(int nr1) {
        this.nr1 = nr1;
    }

    public int getNr2() {
        return nr2;
    }

    public void setNr2(int nr2) {
        this.nr2 = nr2;
    }

    @Override
    public String toString() {
        return "nr1 = " + nr1 + ", nr2=" + nr2;
    }
    // Metoda 1: Verifica daca numerele sunt consecutive în sirul lui Fibonacci
    public boolean suntConsecutiveFibonacci() {
        List<Integer> fibonacci = new ArrayList<>();
        fibonacci.add(0);
        fibonacci.add(1);

        while (fibonacci.get(fibonacci.size() - 1) < Math.max(nr1, nr2)) {
            fibonacci.add(fibonacci.get(fibonacci.size() - 1) + fibonacci.get(fibonacci.size() - 2));
        }
        return fibonacci.contains(nr1) && fibonacci.contains(nr2) &&
                Math.abs(fibonacci.indexOf(nr1) - fibonacci.indexOf(nr2)) == 1;
    }

    // Metoda 2: Cel mai mic multiplu comun (CMMMC)
    public int cmmmc() {
        return (nr1 * nr2) / cmmdc(nr1, nr2);
    }

    private int cmmdc(int a, int b) {
        if (b == 0) return a;
        return cmmdc(b, a % b);
    }

    // Metoda 3: Suma cifrelor este egala
    public boolean sumaCifrelorEgala() {
        return sumaCifrelor(nr1) == sumaCifrelor(nr2);
    }

    private int sumaCifrelor(int numar) {
        int suma = 0;
        while (numar != 0) {
            suma += numar % 10;
            numar /= 10;
        }
        return suma;
    }

    // Metoda 4: Acelasi numar de cifre pare
    public boolean acelasiNumarCifrePare() {
        return numarCifrePare(nr1) == numarCifrePare(nr2);
    }

    private int numarCifrePare(int numar) {
        int count = 0;
        while (numar != 0) {
            if ((numar % 10) % 2 == 0) count++;
            numar /= 10;
        }
        return count;
    }
}
