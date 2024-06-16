package br.edu.ifsul.cstsi.cinema.api.infra;

import br.edu.ifsul.cstsi.cinema.api.usuarios.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceHttpBasic implements UserDetailsService {

    @Autowired
    private UsuarioRepository rep;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return rep.findByUsuario(username);
    }
}
