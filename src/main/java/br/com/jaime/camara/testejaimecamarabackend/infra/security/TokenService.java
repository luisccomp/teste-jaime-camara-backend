package br.com.jaime.camara.testejaimecamarabackend.infra.security;

import br.com.jaime.camara.testejaimecamarabackend.domain.model.Usuario;
import br.com.jaime.camara.testejaimecamarabackend.infra.configuration.TesteJaimeCamaraConfigurationProperties;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import lombok.AllArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
@EnableConfigurationProperties(TesteJaimeCamaraConfigurationProperties.class)
@AllArgsConstructor
public class TokenService {
    private final TesteJaimeCamaraConfigurationProperties testeJaimeCamaraConfigurationProperties;

    public String generateToken(Usuario usuario) {
        var algorithm = Algorithm.HMAC256(testeJaimeCamaraConfigurationProperties.getJwtSecret());

        try {
            return JWT.create()
                    .withIssuer("auth-api")
                    .withSubject(usuario.getLogin())
                    .withExpiresAt(generateExpirationDate())
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Erro ao gerar token JWT", exception);
        }
    }

    public String getLoginFromToken(String token) {
        var algorithm = Algorithm.HMAC256(testeJaimeCamaraConfigurationProperties.getJwtSecret());

        try {
            var decodedToken = JWT.require(algorithm)
                    .withIssuer("auth-api")
                    .build()
                    .verify(token);

            return decodedToken.getSubject();
        } catch (JWTVerificationException exception) {
            return null;
        }
    }

    private Instant generateExpirationDate() {
        return LocalDateTime.now().plusHours(1).toInstant(ZoneOffset.of("-03:00"));
    }
}
