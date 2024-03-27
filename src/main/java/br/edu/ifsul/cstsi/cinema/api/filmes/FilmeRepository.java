package br.edu.ifsul.cstsi.cinema.api.filmes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FilmeRepository extends JpaRepository<Filme, Long> {

    @Query(value= "SELECT f FROM Filme f where f.titulo ilike ?1")
    List<Filme> findByNome(String nome);
}
