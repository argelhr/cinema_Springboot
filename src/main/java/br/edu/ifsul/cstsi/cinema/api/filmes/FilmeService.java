package br.edu.ifsul.cstsi.cinema.api.filmes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FilmeService {

    @Autowired
    private FilmeRepository rep;

    public List<Filme> getFilmes(){
        return rep.findAll();
    }

    public List<Filme> filterByNome(String nome){
        return rep.findByNome("%"+nome+"%");
    }

    public Optional<Filme> getById(Long id){
        return rep.findById(id);
    }
    public Filme insert(Filme f){
        Assert.isNull(f.getId(), "Não foi possivel adicionar um novo filme");
        return rep.save(f);
    }

    public Filme update(Long id, Filme f){
        Assert.notNull(id, "Não é possível atualizar esse filme");

        var opt = rep.findById(id);
        if(opt.isPresent()){
            var db = opt.get();
            db.setTitulo(f.getTitulo());
            db.setMinutos(f.getMinutos());
            return rep.save(db);
        }
        return null;
    }

    public Boolean delete(Long id){
        var opt = rep.findById(id);
        if(opt.isPresent()){
            rep.deleteById(id);
            return true;
        }
        return false;
    }


}
