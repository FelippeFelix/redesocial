package br.com.spring.redeSocial.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.spring.redeSocial.model.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, Integer> {
	
	Usuario findByEmail(String email);
	Usuario findByUsername(String username);
}
