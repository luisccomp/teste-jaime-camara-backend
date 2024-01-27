package br.com.jaime.camara.testejaimecamarabackend.adapters;

import br.com.jaime.camara.testejaimecamarabackend.domain.model.Usuario;
import br.com.jaime.camara.testejaimecamarabackend.domain.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CommandLineRunnerAdapter implements CommandLineRunner {
    private final PasswordEncoder passwordEncoder;

    private final UsuarioRepository usuarioRepository;

    @Override
    public void run(String... args) throws Exception {
        var usuario = new Usuario();
        usuario.setLogin("exemplo@email.com");
        usuario.setSenha(passwordEncoder.encode("senha-forte@123#"));

        usuarioRepository.save(usuario);
    }
}
