package exercitiu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

class ExceptieVarsta extends Exception {
    public ExceptieVarsta(String message) {
        super(message);
    }
}

class ExceptieAnExcursie extends Exception {
    public ExceptieAnExcursie(String message) {
        super(message);
    }
}

class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/lab8";
    private static final String USER = "root";
    private static final String PASSWORD = "parola";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}

public class MainApp {
    public static void main(String[] args) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            PersoanaDAO persoanaDAO = new PersoanaDAO(connection);
            ExcursieDAO excursieDAO = new ExcursieDAO(connection);
            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.println("\n=== Meniu ===");
                System.out.println("1. Adăugare persoană");
                System.out.println("2. Adăugare excursie");
                System.out.println("3. Afișare persoane și excursii");
                System.out.println("4. Afișare excursii pentru o persoană");
                System.out.println("5. Afișare persoane care au vizitat o destinație");
                System.out.println("6. Afișare persoane cu excursii într-un an");
                System.out.println("7. Ștergere excursie");
                System.out.println("8. Ștergere persoană");
                System.out.println("0. Ieșire");
                System.out.print("Alege o opțiune: ");

                int optiune = Integer.parseInt(scanner.nextLine());

                switch (optiune) {
                    case 1:
                        adaugaPersoana(persoanaDAO, scanner);
                        break;
                    case 2:
                        adaugaExcursie(persoanaDAO, excursieDAO, scanner);
                        break;
                    case 3:
                        afiseazaPersoaneSiExcursii(persoanaDAO, excursieDAO);
                        break;
                    case 4:
                        afiseazaExcursiiPentruPersoana(excursieDAO, scanner);
                        break;
                    case 5:
                        afiseazaPersoaneCuDestinatie(excursieDAO, scanner);
                        break;
                    case 6:
                        afiseazaPersoaneCuExcursiiIntrUnAn(excursieDAO, scanner);
                        break;
                    case 7:
                        stergeExcursie(excursieDAO, scanner);
                        break;
                    case 8:
                        stergePersoana(persoanaDAO, excursieDAO, scanner);
                        break;
                    case 0:
                        System.out.println("La revedere!");
                        return;
                    default:
                        System.out.println("Opțiune invalidă!");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void adaugaPersoana(PersoanaDAO persoanaDAO, Scanner scanner) {
        try {
            System.out.print("Introduceți numele persoanei: ");
            String nume = scanner.nextLine();

            System.out.print("Introduceți vârsta persoanei: ");
            int varsta = Integer.parseInt(scanner.nextLine());

            if (varsta < 0 || varsta > 120) {
                throw new ExceptieVarsta("Vârsta trebuie să fie între 0 și 120!");
            }

            persoanaDAO.adaugaPersoana(new Persoana(nume, varsta));
            System.out.println("Persoana a fost adăugată cu succes!");
        } catch (NumberFormatException e) {
            System.out.println("Vârsta trebuie să fie un număr întreg!");
        } catch (ExceptieVarsta e) {
            System.out.println(e.getMessage());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void adaugaExcursie(PersoanaDAO persoanaDAO, ExcursieDAO excursieDAO, Scanner scanner) {
        try {
            System.out.print("Introduceți ID-ul persoanei: ");
            int idPersoana = Integer.parseInt(scanner.nextLine());

            if (!persoanaDAO.existaPersoana(idPersoana)) {
                System.out.println("Persoana cu acest ID nu există!");
                return;
            }

            System.out.print("Introduceți destinația excursiei: ");
            String destinatia = scanner.nextLine();

            System.out.print("Introduceți anul excursiei: ");
            int anul = Integer.parseInt(scanner.nextLine());

            excursieDAO.adaugaExcursie(new Excursie(idPersoana, destinatia, anul));
            System.out.println("Excursia a fost adăugată cu succes!");
        } catch (NumberFormatException e) {
            System.out.println("Anul excursiei trebuie să fie un număr întreg!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void afiseazaPersoaneSiExcursii(PersoanaDAO persoanaDAO, ExcursieDAO excursieDAO) {
        try {
            List<Persoana> persoane = persoanaDAO.getToatePersoanele();

            for (Persoana persoana : persoane) {
                System.out.println("Persoană: " + persoana.getNume() + " (" + persoana.getVarsta() + " ani)");
                List<Excursie> excursii = excursieDAO.getExcursiiPentruPersoana(persoana.getId());

                if (excursii.isEmpty()) {
                    System.out.println("  Nicio excursie.");
                } else {
                    for (Excursie excursie : excursii) {
                        System.out.println("  - " + excursie.getDestinatia() + " (" + excursie.getAnul() + ")");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void afiseazaExcursiiPentruPersoana(ExcursieDAO excursieDAO, Scanner scanner) {
        try {
            System.out.print("Introduceți numele persoanei: ");
            String nume = scanner.nextLine();

            List<Excursie> excursii = excursieDAO.getExcursiiPentruPersoana(nume);

            if (excursii.isEmpty()) {
                System.out.println("Această persoană nu are excursii.");
            } else {
                System.out.println("Excursii pentru " + nume + ":");
                for (Excursie excursie : excursii) {
                    System.out.println("  - " + excursie.getDestinatia() + " (" + excursie.getAnul() + ")");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void afiseazaPersoaneCuDestinatie(ExcursieDAO excursieDAO, Scanner scanner) {
        try {
            System.out.print("Introduceți destinația: ");
            String destinatia = scanner.nextLine();

            List<Persoana> persoane = excursieDAO.getPersoaneCuDestinatie(destinatia);

            if (persoane.isEmpty()) {
                System.out.println("Nicio persoană nu a vizitat această destinație.");
            } else {
                System.out.println("Persoane care au vizitat " + destinatia + ":");
                for (Persoana persoana : persoane) {
                    System.out.println("  - " + persoana.getNume());
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void afiseazaPersoaneCuExcursiiIntrUnAn(ExcursieDAO excursieDAO, Scanner scanner) {
        try {
            System.out.print("Introduceți anul: ");
            int anul = Integer.parseInt(scanner.nextLine());

            List<Persoana> persoane = excursieDAO.getPersoaneCuExcursiiIntrUnAn(anul);

            if (persoane.isEmpty()) {
                System.out.println("Nicio persoană nu a făcut excursii în acest an.");
            } else {
                System.out.println("Persoane care au făcut excursii în anul " + anul + ":");
                for (Persoana persoana : persoane) {
                    System.out.println("  - " + persoana.getNume());
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("Anul trebuie să fie un număr întreg!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void stergeExcursie(ExcursieDAO excursieDAO, Scanner scanner) {
        try {
            System.out.print("Introduceți ID-ul excursiei: ");
            int idExcursie = Integer.parseInt(scanner.nextLine());

            excursieDAO.stergeExcursie(idExcursie);
            System.out.println("Excursia a fost ștearsă cu succes!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void stergePersoana(PersoanaDAO persoanaDAO, ExcursieDAO excursieDAO, Scanner scanner) {
        try {
            System.out.print("Introduceți ID-ul persoanei: ");
            int idPersoana = Integer.parseInt(scanner.nextLine());

            excursieDAO.stergeExcursiiPentruPersoana(idPersoana);
            persoanaDAO.stergePersoana(idPersoana);
            System.out.println("Persoana și excursiile asociate au fost șterse cu succes!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

