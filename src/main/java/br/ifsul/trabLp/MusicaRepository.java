package br.ifsul.trabLp;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MusicaRepository extends JpaRepository<Musica, Long>{

    List<Musica> findAllByArtista(final Artista artista);
}
