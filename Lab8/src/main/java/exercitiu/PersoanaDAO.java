package exercitiu;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersoanaDAO {
    private Connection connection;

    public PersoanaDAO(Connection connection) {
        this.connection = connection;
    }

    public void adaugaPersoana(Persoana persoana) throws SQLException {
        String sql = "INSERT INTO persoane (nume, varsta) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, persoana.getNume());
            stmt.setInt(2, persoana.getVarsta());
            stmt.executeUpdate();
        }
    }

    public boolean existaPersoana(int idPersoana) throws SQLException {
        String sql = "SELECT COUNT(*) FROM persoane WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idPersoana);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            return rs.getInt(1) > 0;
        }
    }

    public List<Persoana> getToatePersoanele() throws SQLException {
        List<Persoana> persoane = new ArrayList<>();
        String sql = "SELECT * FROM persoane";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nume = resultSet.getString("nume");
                int varsta = resultSet.getInt("varsta");
                persoane.add(new Persoana(id, nume, varsta));
            }
        }
        return persoane;
    }

    public void stergePersoana(int id) throws SQLException {
        String sql = "DELETE FROM persoane WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }
}
