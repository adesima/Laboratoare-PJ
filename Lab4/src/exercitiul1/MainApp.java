package exercitiul1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainApp {
    private static void Meniu() {
        System.out.println("\n1. Afisarea tutror echipamentlor");
        System.out.println("2. Afisarea imprimantelor");
        System.out.println("3. Afişarea copiatoarelor");
        System.out.println("4. Afişarea sistemelor de calcul");
        System.out.println("5. Modificarea stării în care se află un echipament");
        System.out.println("6. Setarea unui anumit mod de scriere pentru o imprimantă");
        System.out.println("7. Setarea unui format de copiere pentru copiatoare");
        System.out.println("8. Instalarea unui anumit sistem de operare pe un sistem de calcul");
        System.out.println("9. Afişarea echipamentelor vândute");
        System.out.print("Optiunea dvs. este: ");
    }

    private static void Init(List<Echipament> e) {
        try (Scanner scanner = new Scanner(new FileInputStream("electronice.txt"))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] atributes = line.split(";");
                String name = atributes[0];
                int inventar = Integer.parseInt(atributes[1]);
                double pret = Double.parseDouble(atributes[2]);
                String zona_mag = atributes[3];
                Situatie Situatie = null;

                if (atributes[4].equalsIgnoreCase("achizitionat")) {
                    Situatie = Situatie.achizitionat;
                } else if (atributes[4].equalsIgnoreCase("expus")) {
                    Situatie = Situatie.expus;
                } else if (atributes[4].equalsIgnoreCase("vandut")) {
                    Situatie = Situatie.vandut;
                }

                if (atributes[5].equalsIgnoreCase("imprimanta")) {
                    int ppm = Integer.parseInt(atributes[6]);
                    String dpi = atributes[7];
                    int pCar = Integer.parseInt(atributes[8]);
                    ModulTiparire ModulTiparire = null;

                    if (atributes[9].equalsIgnoreCase("color")) {
                        ModulTiparire = ModulTiparire.color;
                    } else {
                        ModulTiparire = ModulTiparire.alb_negru;
                    }

                    Imprimata imprimanta = new Imprimata(name, inventar, pret, zona_mag, Situatie, atributes[5], ppm, dpi, pCar, ModulTiparire);
                    e.add(imprimanta);
                } else if (atributes[5].equalsIgnoreCase("copiator")) {
                    int pTon = Integer.parseInt(atributes[6]);
                    Format Format = null;

                    if (atributes[7].equalsIgnoreCase("A3")) {
                        Format = Format.A3;
                    }else {
                        Format = Format.A4;
                    }

                    Copiator copiator = new Copiator(name, inventar, pret, zona_mag, Situatie, atributes[5], pTon, Format);
                    e.add(copiator);
                } else if (atributes[5].equalsIgnoreCase("sistem de calcul")) {
                    String tipMon = atributes[6];
                    double vitProc = Double.parseDouble(atributes[7]);
                    int cHdd = Integer.parseInt(atributes[8]);
                    OS os = null;

                    if (atributes[9].equalsIgnoreCase("windows")) {
                        os = OS.Windows;
                    }else {
                        os = OS.Linux;
                    }

                    SistemCalcul sistDeCalcul = new SistemCalcul(name, inventar, pret, zona_mag, Situatie, atributes[5], tipMon, vitProc, cHdd, os);
                    e.add(sistDeCalcul);
                }
            }
        } catch (FileNotFoundException err) {
            throw new RuntimeException(err);
        }
    }

    // 1. Afisarea tutror echipamentlor
    private static void afisareEch(List<Echipament> echipemente) {
        for (Echipament e : echipemente)
            System.out.println(e);
    }

    // 2. Afisarea imprimantelor
    private static void afisareImpr(List<Echipament> echipemente) {
        for (Echipament e : echipemente)
            if (e instanceof Imprimata)
                System.out.println(e);
    }

    // 3. Afişarea copiatoarelor
    private static void afisareCop(List<Echipament> echipemente) {
        for (Echipament e : echipemente)
            if (e instanceof Copiator)
                System.out.println(e);
    }

    // 4. fişarea sistemelor de calcul
    private static void afisareSisCal(List<Echipament> echipemente) {
        for (Echipament e : echipemente)
            if (e instanceof SistemCalcul)
                System.out.println(e);
    }

    private static Echipament cautaEchipament(List<Echipament>echipamente, int nrInv) {
        for (Echipament e : echipamente) {
            if (e.getNr_inv() == nrInv) {
                return e;
            }
        }
        return null;
    }

    // 5. Modificarea stării în care se află un echipament
    private static void modificareStareEch (List<Echipament> echipamente) {
        System.out.print("Introduceti numarul de inventar al echipamentului: ");
        Scanner scanner = new Scanner(System.in);
        int nrInv = scanner.nextInt();
        Echipament e = cautaEchipament(echipamente, nrInv);
        if (e != null) {
            System.out.print("Introduceti noua stare (achizitionat, expus, vandut): ");
            String sit = scanner.next();
            e.setSituatie(Situatie.valueOf(sit));
            System.out.println("Starea echipamentului a fost schimbata.");
        } else {
            System.out.println("Echipamentul nu se afla in depozit.");
        }
    }

    // 6. Setarea unui anumit mod de scriere pentru o imprimantă
    private static void setareModTiparire(List<Echipament> e){
        System.out.print("Introduceti nr de inventar al imprimantei: ");
        Scanner scanner = new Scanner(System.in);
        int nrInv = scanner.nextInt();
        Echipament echipament = cautaEchipament(e, nrInv);
        if (echipament instanceof Imprimata) {
            System.out.print("Introduceti noul mod de tiparire (color, alb_negru): ");
            String mod = scanner.next();
            ((Imprimata) echipament).setMod(ModulTiparire.valueOf(mod));
        } else if (echipament == null){
            System.out.println("Echipamentul nu exista.");
        } else
            System.out.println("Nu este o imprimanta");
    }

    // 7. Setarea unui format de copiere pentru copiatoare
    private static void setareFormatCopiere(List<Echipament> e){
        System.out.print("Introduceți numărul de inventar al copiatorului: ");
        Scanner scanner = new Scanner(System.in);
        int nrInv = scanner.nextInt();
        Echipament echipament = cautaEchipament(e, nrInv);
        if (echipament instanceof Copiator) {
            System.out.print("Introduceți noul format de copiere (A3, A4): ");
            String format = scanner.next();
            ((Copiator) echipament).setFormat(Format.valueOf(format));
        } else {
            System.out.println("Nu este un copiator.");
        }
    }

    // 8. Instalarea unui anumit sistem de operare pe un sistem de calcul
    private static void instalareOS(List<Echipament> e){
        System.out.print("Introduceți numărul de inventar al sistemului de calcul: ");
        Scanner scanner = new Scanner(System.in);
        int nrInv = scanner.nextInt();
        Echipament echipament = cautaEchipament(e, nrInv);
        if (echipament instanceof SistemCalcul) {
            System.out.print("Introduceți noul sistem de operare (Windows, Linux): ");
            String os = scanner.next();
            ((SistemCalcul) echipament).setOs(OS.valueOf(os));
        } else {
            System.out.println("Nu este un sistem de calcul.");
        }
    }

    // 9. Afişarea echipamentelor vândute
    private static void afisEchVandute(List<Echipament> e){
        for (Echipament ech : e) {
            if (ech.getSituatie() == Situatie.vandut) {
                System.out.println(ech);
            }
        }
    }

    public static void main(String[] args) {
        // creare lista
        List<Echipament> e = new ArrayList<Echipament>();
        Init(e);
        while (true)
        {
            Meniu();
            Scanner scanner = new Scanner(System.in);
            int opt = scanner.nextInt();

            switch (opt)
            {
                case 1:
                    afisareEch(e);
                    break;
                case 2:
                    afisareImpr(e);
                    break;
                case 3:
                    afisareCop(e);
                    break;
                case 4:
                    afisareSisCal(e);
                    break;
                case 5:
                    modificareStareEch(e);
                    break;
                case 6:
                    setareModTiparire(e);
                    break;
                case 7:
                    setareFormatCopiere(e);
                    break;
                case 8:
                    instalareOS(e);
                    break;
                case 9:
                    afisEchVandute(e);
                    break;
            }
        }
    }
}
