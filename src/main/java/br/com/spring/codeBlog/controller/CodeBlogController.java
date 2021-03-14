package br.com.spring.codeBlog.controller;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import org.hibernate.service.spi.InjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.spring.codeBlog.model.Postagem;
import br.com.spring.codeBlog.model.Usuario;
import br.com.spring.codeBlog.service.PostagemService;
import br.com.spring.codeBlog.service.UsuarioService;

@Controller
public class CodeBlogController {

	@Autowired
	PostagemService codeBlogService;
	
	@Autowired
	UsuarioService usuarioService;
	
	@Autowired
	Usuario usuarioLogado = new Usuario();
	
	@RequestMapping(value = {"/posts"}, method = RequestMethod.GET)
	public ModelAndView getPosts() {
		ModelAndView mv = new ModelAndView("posts");
		List<Postagem> posts = codeBlogService.findAll();
		
		usuarioLogado = (Usuario) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		
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
		Postagem post = codeBlogService.findById(id);
		mv.addObject("post", post);
		return mv;
	}
	
	@RequestMapping(value = "/newpost", method = RequestMethod.GET)
	public String getPostForm() {
		return "postForm";
	}
	
	@RequestMapping(value = "/newpost", method = RequestMethod.POST)
	public String salvarPost(@Valid Postagem post,  BindingResult result, RedirectAttributes redirectAttributes) {
		if(result.hasErrors()) {
			redirectAttributes.addFlashAttribute("mensagem", "Verifique se os campos obrigatórios foram preenchidos!");
			return "redirect:/newpost";
		}
		post.setAutor(usuarioLogado);
		post.setData(LocalDate.now());
		codeBlogService.save(post);
		return "redirect:/posts";
	}
	
	@RequestMapping(value = "/editarPost{id}", method = RequestMethod.GET)
	public ModelAndView getPostFormEdit(@PathVariable("id") Long id) {
		ModelAndView mv = new ModelAndView("postFormEdit");
		Postagem post = codeBlogService.findById(id);
		mv.addObject("post", post);
		return mv;
	}
	
	@RequestMapping(value = { "/posts{id}" }, method = RequestMethod.POST)
	public String getPostRemove(@Valid Postagem post,  BindingResult result) {
		if(result.hasErrors()) {
			System.out.println("Id do post a ser removido: " + post.getId());
		}
		System.out.println("Id do post a ser removido: " + post.getId());
		post = codeBlogService.findById(post.getId());
		codeBlogService.remove(post);

		return "redirect:/posts";
	}
	
	@RequestMapping(value = "/editarPost{id}", method = RequestMethod.POST)
	public String editarPost(@Valid Postagem post,  BindingResult result, RedirectAttributes redirectAttributes) {
		if(result.hasErrors()) {
			redirectAttributes.addFlashAttribute("mensagem", "Verifique se os campos obrigatórios foram preenchidos!");
			return "redirect:/newpost";
		}
		
		if (post.getData() == null) {
			post.setData(LocalDate.now());
		}
		codeBlogService.save(post);
		
		System.out.println("Id ao salvar: " + post.getData());
		
		return "redirect:/posts";
	}
	
	
	
	
}
