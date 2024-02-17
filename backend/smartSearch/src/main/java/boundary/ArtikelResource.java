package boundary;

import dto.ArtikelDTO;
import jakarta.inject.Inject;
import model.Artikel;
import service.ArtikelService;
import util.ArtikelMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import java.util.List;

@Path("/artikel")
@Produces(MediaType.APPLICATION_JSON)
public class ArtikelResource {

    private static final Logger logger = LogManager.getLogger(ArtikelResource.class);

    @Inject
    ArtikelMapper artikelMapper;

    @Inject
    ArtikelService artikelService;

    @GET
    @Path("suche")
    public List<ArtikelDTO> sucheMitEan(@QueryParam("ean") String ean) {
        logger.info("Suche nach Artikeln mit EAN beginnend mit: {}", ean);
        List<Artikel> artikelListe = artikelService.sucheNachEan(ean);
        if(artikelListe.isEmpty()) {
            logger.info("Keine Artikel gefunden für EAN: {}", ean);
        } else {
            logger.info("Gefundene Artikelanzahl für EAN {}: {}", ean, artikelListe.size());
        }
        return artikelMapper.mapListToDTO(artikelListe);
    }
}
