package br.edu.ifsul.cstsi.cinema.api.autenticacao;

import br.edu.ifsul.cstsi.cinema.api.usuarios.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AutenticacaoService implements UserDetailsService{
    @Autowired
    private AutenticacaoRepository rep;
    @Override
    public UserDetails loadUserByUsername(String usuario) throws UsernameNotFoundException {
        return rep.findByUsuario(usuario);
    }

}
