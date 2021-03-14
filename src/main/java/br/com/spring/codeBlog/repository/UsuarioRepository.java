package br.com.spring.codeBlog.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.spring.codeBlog.model.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, Integer> {
	
	Usuario findByEmail(String email);
	Usuario findByUsername(String username);
}
