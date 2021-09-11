package br.com.spring.redesocial.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.spring.redesocial.model.Postagem;
import br.com.spring.redesocial.model.Usuario;

public interface PostagemRepository extends JpaRepository<Postagem, Long> {
	
	List<Postagem> findAllByAutor(Usuario autor);
}
