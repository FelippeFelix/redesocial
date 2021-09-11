package br.com.spring.redesocial.service;

import java.util.List;

import br.com.spring.redesocial.model.Postagem;
import br.com.spring.redesocial.model.Usuario;

public interface PostagemService {

	List<Postagem> findAll();
	List<Postagem> findAllByAutor(Usuario autor);
	Postagem findById(Long id);
	Postagem save(Postagem post);
	void remove(Postagem post);

}
