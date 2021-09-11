package br.com.spring.redesocial.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.spring.redesocial.model.Comentario;
import br.com.spring.redesocial.model.Postagem;
import br.com.spring.redesocial.model.Usuario;
import br.com.spring.redesocial.service.ComentarioService;
import br.com.spring.redesocial.service.PostagemService;
import br.com.spring.redesocial.service.UsuarioService;

@Controller
public class PostagemController {

	@Autowired
	private PostagemService postagemService;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private ComentarioService comentarioService;
	
	private Usuario usuarioLogado;
	
	private Postagem post;
	
	@RequestMapping(value = {"/posts"}, method = RequestMethod.GET)
	public ModelAndView getPosts() {
		ModelAndView mv = new ModelAndView("posts");
		List<Postagem> posts = postagemService.findAll();
		
		usuarioLogado = usuarioService.findByUsername(SecurityContextHolder.getContext()
				.getAuthentication().getName());
		
		mv.addObject("posts", posts);
		mv.addObject("usuarioLogado", usuarioLogado);
		return mv;
	}
	
	@RequestMapping(value = {"/"}, method = RequestMethod.GET)
	public String paginaBarra() {
		return "redirect:/posts";
	}
	
	@RequestMapping(value = "/posts/{id}", method = RequestMethod.GET)
	public ModelAndView getPostDetails(@PathVariable("id") Long id) {
		ModelAndView mv = new ModelAndView("postDetails");
		post = postagemService.findById(id);
		
		List<Comentario> comentarios =  comentarioService.findAllByPostagem(post);
		
		mv.addObject("post", post);
		mv.addObject("comentarios", comentarios);
		return mv;
	}
	
	@RequestMapping(value = "/newpost", method = RequestMethod.GET)
	public String getPostForm() {
		return "postForm";
	}
	
	@PostMapping("/newpost")
	public String salvarPost(@Valid Postagem post, BindingResult result, RedirectAttributes redirectAttributes) {
		if(result.hasErrors()) {
			redirectAttributes.addFlashAttribute("mensagem", "Verifique se os campos obrigatórios foram preenchidos!");
			return "redirect:/newpost";
		}
		post.setAutor(usuarioLogado);
		post.setData(LocalDate.now());
		postagemService.save(post);
		return "redirect:/posts";
	}
	
	@GetMapping("/post/editar/{id}")
	public ModelAndView formEdit(@PathVariable("id") Long id) {
		ModelAndView mv = new ModelAndView("postFormEdit");
		Postagem post = postagemService.findById(id);
		mv.addObject("post", post);
		return mv;
	}
	
	@DeleteMapping("/post/remover/{id}")
	public ResponseEntity<?> getPostRemove(@PathVariable("id") Long id) {
		Postagem post = postagemService.findById(id);
	
		System.out.println("Id do post a ser removido: " + post.getId());
		
		postagemService.remove(post);

		return ResponseEntity.ok().build();
	}
	
	@RequestMapping(value = "/editarPost{id}", method = RequestMethod.POST)
	public String editarPost(@Valid Postagem post, BindingResult result, RedirectAttributes redirectAttributes) {
		if(result.hasErrors()) {
			redirectAttributes.addFlashAttribute("mensagem", "Verifique se os campos obrigatórios foram preenchidos!");
			return "redirect:/newpost";
		}
		
		if (post.getData() == null) {
			post.setData(LocalDate.now());
		}
		postagemService.save(post);
		
		System.out.println("Id ao salvar: " + post.getData());
		
		return "redirect:/posts";
	}
	
	@RequestMapping(value = "/posts/{id}", method = RequestMethod.POST)
	public String novoComentario(@Valid Comentario comentario, 
		BindingResult result, RedirectAttributes redirectAttributes)
	{
		if(result.hasErrors()) {
			redirectAttributes.addFlashAttribute("mensagem", "Verifique se os campos obrigatórios foram preenchidos!");
			return "redirect:/newpost";
		}
		
		usuarioLogado = usuarioService.findByUsername(SecurityContextHolder.getContext()
				.getAuthentication().getName());
		
		comentario.setData(LocalDateTime.now());
		comentario.setAutor(usuarioLogado);
		comentario.setPostagem(post);
		
		System.out.println("Tentando criar novo comentário!");
		comentarioService.save(comentario);
		
		return "redirect:/posts";
	}
	
}
