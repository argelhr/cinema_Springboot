package br.edu.ifsul.cstsi.cinema.api.ingressos;

import br.edu.ifsul.cstsi.cinema.api.sessoes.Sessao;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Entity(name = "Ingresso")
@Table(name = "ingressos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ingresso {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "sessao_id",referencedColumnName = "id",nullable = false)
    private Sessao sessoes;
}
