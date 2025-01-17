package exercitiu;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ExcursieDAO {
    private final Connection connection;

    public ExcursieDAO(Connection connection) {
        this.connection = connection;
    }

    public void adaugaExcursie(Excursie excursie) throws SQLException {
        String sql = "INSERT INTO excursii (id_persoana, destinatia, anul) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, excursie.getIdPersoana());
            statement.setString(2, excursie.getDestinatia());
            statement.setInt(3, excursie.getAnul());
            statement.executeUpdate();
        }
    }

    public List<Excursie> getExcursiiPentruPersoana(int idPersoana) throws SQLException {
        List<Excursie> excursii = new ArrayList<>();
        String sql = "SELECT * FROM excursii WHERE id_persoana = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, idPersoana);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int idExcursie = resultSet.getInt("id_excursie");
                String destinatia = resultSet.getString("destinatia");
                int anul = resultSet.getInt("anul");
                excursii.add(new Excursie(idExcursie, idPersoana, destinatia, anul));
            }
        }
        return excursii;
    }

    public List<Excursie> getExcursiiPentruPersoana(String nume) throws SQLException {
        List<Excursie> excursii = new ArrayList<>();
        String sql = "SELECT e.* FROM excursii e JOIN persoane p ON e.id_persoana = p.id WHERE p.nume = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, nume);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int idExcursie = resultSet.getInt("id_excursie");
                int idPersoana = resultSet.getInt("id_persoana");
                String destinatia = resultSet.getString("destinatia");
                int anul = resultSet.getInt("anul");
                excursii.add(new Excursie(idExcursie, idPersoana, destinatia, anul));
            }
        }
        return excursii;
    }

    public List<Persoana> getPersoaneCuDestinatie(String destinatia) throws SQLException {
        List<Persoana> persoane = new ArrayList<>();
        String sql = "SELECT DISTINCT p.* FROM persoane p JOIN excursii e ON p.id = e.id_persoana WHERE e.destinatia = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, destinatia);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nume = resultSet.getString("nume");
                int varsta = resultSet.getInt("varsta");
                persoane.add(new Persoana(id, nume, varsta));
            }
        }
        return persoane;
    }

    public List<Persoana> getPersoaneCuExcursiiIntrUnAn(int anul) throws SQLException {
        List<Persoana> persoane = new ArrayList<>();
        String sql = "SELECT DISTINCT p.* FROM persoane p JOIN excursii e ON p.id = e.id_persoana WHERE e.anul = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, anul);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nume = resultSet.getString("nume");
                int varsta = resultSet.getInt("varsta");
                persoane.add(new Persoana(id, nume, varsta));
            }
        }
        return persoane;
    }

    public void stergeExcursie(int idExcursie) throws SQLException {
        String sql = "DELETE FROM excursii WHERE id_excursie = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, idExcursie);
            statement.executeUpdate();
        }
    }

    public void stergeExcursiiPentruPersoana(int idPersoana) throws SQLException {
        String sql = "DELETE FROM excursii WHERE id_persoana = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, idPersoana);
            statement.executeUpdate();
        }
    }
}
