package br.edu.ifsul.cstsi.cinema.api.usuarios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/login")
public class AutenticacaoController {
    @Autowired
    private AuthenticationManager manager;

    @PostMapping
    public ResponseEntity login(@RequestBody UsuarioDTO usuarioDTO){
        var authenticationDTO = new UsernamePasswordAuthenticationToken(usuarioDTO.usuario(), usuarioDTO.senha());
        manager.authenticate(authenticationDTO);
        return ResponseEntity.ok().build();
    }
}
