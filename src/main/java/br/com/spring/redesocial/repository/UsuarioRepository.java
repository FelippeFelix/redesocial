package br.com.spring.redesocial.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.spring.redesocial.model.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, Integer> {
	
	Usuario findByEmail(String email);
	Usuario findByUsername(String username);
}
