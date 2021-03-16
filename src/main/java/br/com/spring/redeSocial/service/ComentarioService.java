package br.com.spring.redeSocial.service;

import java.util.List;

import br.com.spring.redeSocial.model.Comentario;
import br.com.spring.redeSocial.model.Postagem;

public interface ComentarioService {
	List<Comentario> findAllByPostagem(Postagem postagem);
	Comentario save(Comentario comentario);
	Comentario findById(Integer id);
	void remove(Comentario comentario);
}
