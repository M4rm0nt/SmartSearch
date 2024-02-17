package service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import model.Artikel;
import repository.ArtikelRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.List;

@ApplicationScoped
public class ArtikelService {

    private static final Logger logger = LogManager.getLogger(ArtikelService.class);

    private final ArtikelRepository artikelRepository;

    @Inject
    public ArtikelService(ArtikelRepository artikelRepository) {
        this.artikelRepository = artikelRepository;
    }

    public List<Artikel> sucheNachEan(String ean) {
        logger.info("Starte Suche nach EAN: {}", ean);
        List<Artikel> result = artikelRepository.findByEanStartingWith(ean);
        if(result.isEmpty()) {
            logger.info("Keine Artikel gefunden für EAN: {}", ean);
        } else {
            logger.info("Suche abgeschlossen für EAN: {}. Gefundene Artikelanzahl: {}", ean, result.size());
        }
        return result;
    }
}
