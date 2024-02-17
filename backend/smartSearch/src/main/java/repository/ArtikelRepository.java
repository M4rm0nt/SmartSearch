package repository;

import jakarta.enterprise.context.ApplicationScoped;
import model.Artikel;
import util.DatabaseConnection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class ArtikelRepository {

    private static final Logger logger = LogManager.getLogger(ArtikelRepository.class);

    public List<Artikel> findByEanStartingWith(String eanPrefix) {
        logger.info("Datenbankabfrage für Artikel mit EAN beginnend mit: {}", eanPrefix);
        List<Artikel> artikelListe = new ArrayList<>();
        String sql = "SELECT * FROM artikel WHERE ean LIKE ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, eanPrefix + "%");
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Artikel artikel = new Artikel(
                            rs.getLong("id"),
                            rs.getString("name"),
                            rs.getString("ean")
                    );
                    artikelListe.add(artikel);
                }
            }
        } catch (SQLException e) {
            logger.error("Fehler bei der Datenbankabfrage für EAN beginnend mit {}: {}", eanPrefix, e.getMessage(), e);
        }
        return artikelListe;
    }
}
