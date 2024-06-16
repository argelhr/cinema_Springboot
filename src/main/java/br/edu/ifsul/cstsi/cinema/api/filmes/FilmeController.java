package br.edu.ifsul.cstsi.cinema.api.filmes;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api/v1/filmes")
@Api(value = "Filmes")
public class FilmeController {

    @Autowired
    private FilmeService service;

    @GetMapping
    @ApiOperation(value = "Retorna todos os filmes cadastrados")
    public ResponseEntity<List<Filme>> selectAll() {
        List<Filme> filmes = service.getFilmes();
        return ResponseEntity.ok(filmes);
    }

    @GetMapping("/filme/nome/{nome}")
    @ApiOperation(value = "Retorna os filmes com o nome filtado")
    public ResponseEntity<List<Filme>> selectByNome(@PathVariable("nome") String nome) {
        List<Filme> filmes = service.filterByNome(nome);

        return filmes.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(filmes);
    }

    @GetMapping("/filme/id/{id}")
    @ApiOperation(value = "Retorna um filme de acordo com o id")
    public ResponseEntity<Filme> selectById(@PathVariable("id") Long id) {

        var f = service.getById(id);

        return f.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.noContent().build());
    }

    @PostMapping
    @Secured({"ROLE_ADMIN"})
    @ApiOperation(value = "Insere um filme")
    public ResponseEntity<String> insert(@RequestBody Filme f) {
        Filme filme = service.insert(f);
        URI location = getUri(filme.getId());
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    @Secured({"ROLE_ADMIn"})
    public ResponseEntity<Filme> update(@PathVariable("id") Long id, @RequestBody Filme f) {
        f.setId(id);
        var retorno = service.update(id, f);
        return retorno != null ?
                ResponseEntity.ok().build() :
                ResponseEntity.notFound().build();
    }

    @DeleteMapping({"/{id}"})
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<String> delete(@PathVariable("id") Long id){
        return service.delete(id)
                ? ResponseEntity.ok().build()
                : ResponseEntity.notFound().build();
    }

    private URI getUri(Long id) {
        return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
    }
}
