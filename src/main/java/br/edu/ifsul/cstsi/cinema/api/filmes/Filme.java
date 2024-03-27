package br.edu.ifsul.cstsi.cinema.api.filmes;

import br.edu.ifsul.cstsi.cinema.api.sessoes.Sessao;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import java.util.Collection;

@Entity(name = "Filme")
@Table(name = "filmes")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Filme {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private Integer minutos;
    @OneToMany(mappedBy = "filme")
    private Collection<Sessao> sessoes;

    public static Filme create(FilmeDTO f){
        ModelMapper mp = new ModelMapper();
        return mp.map(f,Filme.class);
    }

}
