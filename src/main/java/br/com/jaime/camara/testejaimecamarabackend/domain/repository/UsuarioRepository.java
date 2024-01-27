package br.com.jaime.camara.testejaimecamarabackend.domain.repository;

import br.com.jaime.camara.testejaimecamarabackend.domain.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByLogin(String login);
}
