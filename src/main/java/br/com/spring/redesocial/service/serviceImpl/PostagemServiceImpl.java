package br.com.spring.redesocial.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.spring.redesocial.model.Postagem;
import br.com.spring.redesocial.model.Usuario;
import br.com.spring.redesocial.repository.PostagemRepository;
import br.com.spring.redesocial.service.PostagemService;

@Service
public class PostagemServiceImpl implements PostagemService {

	@Autowired
	PostagemRepository postagemRepository;
	
	@Override
	public List<Postagem> findAll() {
		return postagemRepository.findAll();
	}

	@Override
	public List<Postagem> findAllByAutor(Usuario autor) {
		return postagemRepository.findAllByAutor(autor);
	}
	
	@Override
	public Postagem findById(Long id) {
		return postagemRepository.findById(id).get();
	}

	@Override
	public Postagem save(Postagem post) {
		return postagemRepository.save(post);
	}

	@Override
	public void remove(Postagem post) {
		postagemRepository.delete(post);
		
	}

}
