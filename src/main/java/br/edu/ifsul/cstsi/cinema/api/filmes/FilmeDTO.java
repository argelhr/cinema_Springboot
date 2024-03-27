package br.edu.ifsul.cstsi.cinema.api.filmes;


import org.modelmapper.ModelMapper;

public class FilmeDTO {

    private Long id;
    private String titulo;
    private Integer minutos;

    public static FilmeDTO create (Filme f){
        ModelMapper mp = new ModelMapper();
        return mp.map(f, FilmeDTO.class);

    }

}
