package br.com.jaime.camara.testejaimecamarabackend.service.impl;

import br.com.jaime.camara.testejaimecamarabackend.domain.repository.UsuarioRepository;
import br.com.jaime.camara.testejaimecamarabackend.exception.messages.UsuarioErrorMessages;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return usuarioRepository.findByLogin(username)
                .orElseThrow(() -> new UsernameNotFoundException(UsuarioErrorMessages.INVALID_USERNAME_PASSWORD));
    }
}
