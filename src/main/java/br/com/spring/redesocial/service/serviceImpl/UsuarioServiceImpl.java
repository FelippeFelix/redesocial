package br.com.spring.redesocial.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.spring.redesocial.model.Usuario;
import br.com.spring.redesocial.repository.UsuarioRepository;
import br.com.spring.redesocial.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService, UserDetailsService {

	@Autowired
	UsuarioRepository usuarioRepository;

	@Override
	public List<Usuario> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Usuario findById(Integer id) {
		// TODO Auto-generated method stub
		return usuarioRepository.findById(id).get();
	}

	@Override
	public Usuario save(Usuario usuario) {
		// TODO Auto-generated method stub
		return usuarioRepository.save(usuario);
	}

	@Override
	public void remove(Usuario usuario) {
		// TODO Auto-generated method stub

	}
	
	@Override @Transactional(readOnly = true)
	public Usuario findByEmail(String email) {
		
		return usuarioRepository.findByEmail(email);
	}

	@Override
	public Usuario findByUsername(String username) {
		
		return usuarioRepository.findByUsername(username);
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		try {
			Usuario usuario = usuarioRepository.findByUsername(username);
			if (usuario == null) {
				return null;
			}
			System.out.println("Usuário achado: " + usuario.getUsername());
			
			return usuario;
		} catch (Exception e) {
			throw new UsernameNotFoundException("Usuario não encontrado");
		}

	}
	
	/*
	private Set<GrantedAuthority> getAuthorities(Usuario usuario) {
		Set<GrantedAuthority> authorities = new HashSet<>();
		for (Role role : usuario.getRoles()) {
			GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(role.getRole());
			authorities.add(grantedAuthority);
		}
		return authorities;
	}
	*/
	
	
}
