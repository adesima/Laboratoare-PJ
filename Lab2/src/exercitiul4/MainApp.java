package exercitiul4;

/*4. Să se realizeze un program care citește numele si CNP-ul pe care îl au n persoane.
Valoarea lui n se citește de la tastatură. Programul va afișa informațiile introduse și în plus
pentru fiecare persoana va afișa vârsta. Cât timp un CNP-ul este introdus greșit programul va
cere reintroducerea acestuia. Pentru simplitate se consideră că CNP-ul este valid dacă
îndeplinește următoarele condiții:
• Are 13 caractere
• Toate caracterele sunt cifre
• Prima cifră are una din valorile 1, 2, 5, 6
• Cifra de control a CNP-ului are o valoare validă.
Detalii legate de semnificația cifrelor din codul numeric personal şi de modul de calcul al
cifrei de control se găsesc pe link-ul:
https://www.scientia.ro/stiinta-la-minut/128-cultura-economie/459-cnp-codulnumeric-personal.html
Se va crea clasa Persoana cu variabile membre private nume (String) şi cnp (String).
Clasa va avea constructor cu parametri, gettere si settere în funcție de necesități şi metoda
getVarsta() care va calcula şi va returna vârsta persoanei extrăgând data nașterii din CNP şi
citind din sistem data curentă. Se va utiliza clasa LocalDate. Se va crea un vector în care se
vor adăuga obiectele de tip Persoana. Fiecare element din vectorul va fi afișat pe un rând în
formatul nume, CNP, varsta*/

// CNP : S AA LL ZZ JJ NNN C
//       0 12 34 56        13

// control: 279146358279 ... % 11 = C

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainApp {
    public static boolean cifre(String cnp) {
        for (int i = 0; i < cnp.length(); i++)
            if (cnp.charAt(i) < 48 && cnp.charAt(i) > 57)
                return false;
        return true;
    }

    public static boolean sex(String cnp) {
        if (cnp.charAt(0) == '1' || cnp.charAt(0) == '2' || cnp.charAt(0) == '5' || cnp.charAt(0) == '6')
            return true;
        else return false;
    }

    public static boolean control(String cnp) {
        int sum = 0;
        String control = "279146358279";
        for (int i = 0; i < 12; i++) {
            sum += (cnp.charAt(i) - 48) * (control.charAt(i) - 48);
        }
        if (sum % 11 == cnp.charAt(12) - 48) return true;
        else return false;
    }

    public static Persoana citire() {
        Scanner scanner = new Scanner(System.in);
        String nume, cnp = "";

        // Citire nume
        System.out.print("Nume: ");
        nume = scanner.nextLine();

        // Citire CNP
        int ok = 0;
        while (ok == 0) {
            System.out.print("CNP: ");
            cnp = scanner.next();

            // Validare CNP
            if (!cifre(cnp)) ok = 0;
            else if (cnp.length() != 13) ok = 0;
            else if (!sex(cnp)) ok = 0;
            else if (!control(cnp)) ok = 0;
            else ok = 1;

            if (ok == 0) System.out.println("CNP invalid!");
        }
        return new Persoana(nume, cnp);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Citire n
        System.out.println("Cate persoane doriti sa introduceti?");
        int n = scanner.nextInt();

        // Citire persoane si introducere in lista
        Persoana p = null;
        List<Persoana> persoane = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            p = citire();
            persoane.add(p);
        }

        // Afisare persoane
        for (Persoana persoana : persoane)
            System.out.println(persoana);
    }
}
