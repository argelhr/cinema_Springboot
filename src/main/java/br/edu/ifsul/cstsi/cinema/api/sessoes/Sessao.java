package br.edu.ifsul.cstsi.cinema.api.sessoes;

import br.edu.ifsul.cstsi.cinema.api.filmes.Filme;
import br.edu.ifsul.cstsi.cinema.api.ingressos.Ingresso;
import br.edu.ifsul.cstsi.cinema.api.salas.Sala;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Data
@Entity(name = "Sessao")
@Table(name = "sessoes")
@AllArgsConstructor
@NoArgsConstructor
public class Sessao {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "sala_id", referencedColumnName = "id", nullable = false)
    private Sala sala;

    @ManyToOne
    @JoinColumn(name = "filme_id",referencedColumnName = "id",nullable = false)
    private Filme filme;

    @OneToMany(mappedBy = "sessoes")
    private Collection<Ingresso> ingressos;

}
