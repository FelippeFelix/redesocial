package br.com.spring.redeSocial.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.spring.redeSocial.model.Comentario;
import br.com.spring.redeSocial.model.Postagem;

public interface ComentarioRepository extends JpaRepository<Comentario, Integer> {
	List<Comentario> findAllByPostagem(Postagem postagem);
}
