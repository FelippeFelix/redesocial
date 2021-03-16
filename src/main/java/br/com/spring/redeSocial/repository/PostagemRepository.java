package br.com.spring.redeSocial.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.spring.redeSocial.model.Postagem;
import br.com.spring.redeSocial.model.Usuario;

public interface PostagemRepository extends JpaRepository<Postagem, Long> {
	
	List<Postagem> findAllByAutor(Usuario autor);
}
