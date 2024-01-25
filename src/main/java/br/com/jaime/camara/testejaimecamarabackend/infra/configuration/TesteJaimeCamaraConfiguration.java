package br.com.jaime.camara.testejaimecamarabackend.infra.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class TesteJaimeCamaraConfiguration {
    @Bean
    @Scope("singleton")
    ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
