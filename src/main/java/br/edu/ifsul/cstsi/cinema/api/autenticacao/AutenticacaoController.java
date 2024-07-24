package br.edu.ifsul.cstsi.cinema.api.autenticacao;

import br.edu.ifsul.cstsi.cinema.api.infra.TokenJwtDTO;
import br.edu.ifsul.cstsi.cinema.api.infra.TokenService;
import br.edu.ifsul.cstsi.cinema.api.usuarios.Usuario;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/login")
public class AutenticacaoController {
    private static final Logger logger = LoggerFactory.getLogger(AutenticacaoController.class);

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<TokenJwtDTO> login(@RequestBody UsuarioDTO data) {
        logger.debug("Tentativa de login para o usuário: {}", data.usuario());

        var authenticationDTO = new UsernamePasswordAuthenticationToken(data.usuario(), data.senha());
        var authentication = manager.authenticate(authenticationDTO);
        var tokenJWT = tokenService.geraToken((Usuario) authentication.getPrincipal());

        logger.debug("Token JWT gerado: {}", tokenJWT);

        return ResponseEntity.ok(new TokenJwtDTO(tokenJWT));
    }
}
