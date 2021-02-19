package br.ifsul.trabLp;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtistaRepository extends JpaRepository<Artista, Long> {

    List<Artista> findAllByFestivaisIn(final List<Festival> festivais);

    List<Artista> findByFestivaisNotIn(final List<Festival> festivais);

}
