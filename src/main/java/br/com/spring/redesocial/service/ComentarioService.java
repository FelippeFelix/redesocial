package br.com.spring.redesocial.service;

import java.util.List;

import br.com.spring.redesocial.model.Comentario;
import br.com.spring.redesocial.model.Postagem;

public interface ComentarioService {
	List<Comentario> findAllByPostagem(Postagem postagem);
	Comentario save(Comentario comentario);
	Comentario findById(Integer id);
	void remove(Comentario comentario);
}
