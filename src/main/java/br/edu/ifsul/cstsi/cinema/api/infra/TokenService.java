package br.edu.ifsul.cstsi.cinema.api.infra;

import br.edu.ifsul.cstsi.cinema.api.autenticacao.AutenticacaoController;
import br.edu.ifsul.cstsi.cinema.api.infra.TokenInvalidoException;
import br.edu.ifsul.cstsi.cinema.api.usuarios.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service //indica que essa classe deve ser adicionada ao Contexto do aplicativo como um Bean da camada de serviço de segurança JWT
public class TokenService {

    @Value(value = "${api.security.token.secret}") //vem de application.properties, em api.security.token.secret
    private String secret;

    public String geraToken(Usuario usuario){
        try {
            var algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("API Cinema")
                    .withSubject(usuario.getUsername())
                    .withIssuedAt(LocalDateTime.now().toInstant(ZoneOffset.of("-03:00")))
                    .sign(algorithm);
        } catch (JWTCreationException exception){
            // Invalid Signing configuration / Couldn't convert Claims.
            throw new RuntimeException("Erro ao gerar o token JWT.", exception);
        }
    }

    public String getSubject(String tokenJWT){
        try {
            var algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("API Cinema")
                    .build()
                    .verify(tokenJWT)
                    .getSubject();
        } catch (JWTVerificationException exception){
            throw new TokenInvalidoException("Token JWT inválido ou expirado.");
        }
    }

    private Instant dataExpiracao() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00")); //expira em 2 horas
    }

}