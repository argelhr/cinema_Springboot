package br.edu.ifsul.cstsi.cinema.api.usuarios;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/v1/login")
public class AutenticacaoController {
    private AuthenticationManager manager;

    public ResponseEntity<String> login(UsuarioDTO usuarioDTO){
        var authenticationDTO = new UsernamePasswordAuthenticationToken(usuarioDTO.usuario(), usuarioDTO.senha());
        manager.authenticate(authenticationDTO);
        return ResponseEntity.ok().build();
    }
}
