package br.ifsul.trabLp;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FestivalRepository extends JpaRepository<Festival, Long> {

    List<Festival> findAllByArtistasIn(final List<Artista> artistas);
}
