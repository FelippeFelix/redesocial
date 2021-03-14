package br.com.spring.codeBlog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.spring.codeBlog.model.Postagem;
import br.com.spring.codeBlog.model.Usuario;

public interface PostagemRepository extends JpaRepository<Postagem, Long> {
	
	List<Postagem> findAllByAutor(Usuario autor);
}
