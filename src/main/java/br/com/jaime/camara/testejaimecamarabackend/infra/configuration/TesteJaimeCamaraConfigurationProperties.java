package br.com.jaime.camara.testejaimecamarabackend.infra.configuration;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "teste.jaime-camara")
@Data
public class TesteJaimeCamaraConfigurationProperties {
    @NotBlank
    private String jwtSecret;
}
