package util;

import dto.ArtikelDTO;
import jakarta.enterprise.context.ApplicationScoped;
import model.Artikel;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class ArtikelMapper {

    public ArtikelDTO mapToDTO(Artikel artikel) {
        return new ArtikelDTO(artikel.getId(), artikel.getName(), artikel.getEan());
    }

    public List<ArtikelDTO> mapListToDTO(List<Artikel> artikelListe) {
        return artikelListe.stream().map(this::mapToDTO).collect(Collectors.toList());
    }
}
