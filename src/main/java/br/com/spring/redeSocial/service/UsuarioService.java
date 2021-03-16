package br.com.spring.redeSocial.service;

import java.util.List;

import br.com.spring.redeSocial.model.Usuario;

public interface UsuarioService{
	List<Usuario> findAll();
	Usuario findById(Integer id);
	Usuario findByEmail(String email);
	Usuario findByUsername(String username);
	Usuario save(Usuario usuario);
	void remove(Usuario usuario);

}
