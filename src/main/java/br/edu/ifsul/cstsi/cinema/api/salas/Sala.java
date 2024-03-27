package br.edu.ifsul.cstsi.cinema.api.salas;

import br.edu.ifsul.cstsi.cinema.api.sessoes.Sessao;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Data
@Entity(name = "Sala")
@Table(name = "salas")
@AllArgsConstructor
@NoArgsConstructor
public class Sala {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer assentos;

    @OneToMany(mappedBy = "sala")
    private Collection<Sessao> sessoes;

}
