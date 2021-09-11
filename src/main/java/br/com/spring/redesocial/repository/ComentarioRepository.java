package br.com.spring.redesocial.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.spring.redesocial.model.Comentario;
import br.com.spring.redesocial.model.Postagem;

public interface ComentarioRepository extends JpaRepository<Comentario, Integer> {
	List<Comentario> findAllByPostagem(Postagem postagem);
}
