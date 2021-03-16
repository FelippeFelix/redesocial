package br.com.spring.redeSocial.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.spring.redeSocial.model.Comentario;
import br.com.spring.redeSocial.model.Postagem;
import br.com.spring.redeSocial.repository.ComentarioRepository;
import br.com.spring.redeSocial.service.ComentarioService;

@Service
public class ComentarioServiceImpl implements ComentarioService{

	@Autowired
	ComentarioRepository comentarioRepository;
	
	@Override
	public List<Comentario> findAllByPostagem(Postagem postagem) {
		return comentarioRepository.findAllByPostagem(postagem);
	}

	@Override
	public Comentario save(Comentario comentario) {
		return comentarioRepository.save(comentario);
	}

	@Override
	public void remove(Comentario comentario) {
		comentarioRepository.delete(comentario);
		
	}

	@Override
	public Comentario findById(Integer id) {
		return comentarioRepository.findById(id).get();
	}

}
