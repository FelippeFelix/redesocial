package br.com.spring.codeBlog.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.spring.codeBlog.model.Postagem;
import br.com.spring.codeBlog.model.Usuario;
import br.com.spring.codeBlog.repository.PostagemRepository;
import br.com.spring.codeBlog.service.PostagemService;

@Service
public class PostagemServiceImpl implements PostagemService {

	@Autowired
	PostagemRepository codeBlogRepository;
	
	@Override
	public List<Postagem> findAll() {
		return codeBlogRepository.findAll();
	}

	@Override
	public List<Postagem> findAllByAutor(Usuario autor) {
		return codeBlogRepository.findAllByAutor(autor);
	}
	
	@Override
	public Postagem findById(Long id) {
		return codeBlogRepository.findById(id).get();
	}

	@Override
	public Postagem save(Postagem post) {
		return codeBlogRepository.save(post);
	}

	@Override
	public void remove(Postagem post) {
		codeBlogRepository.delete(post);
		
	}

}
