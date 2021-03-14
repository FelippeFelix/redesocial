package br.com.spring.codeBlog.controller;

import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.spring.codeBlog.model.Postagem;
import br.com.spring.codeBlog.model.Role;
import br.com.spring.codeBlog.model.Usuario;
import br.com.spring.codeBlog.service.PostagemService;
import br.com.spring.codeBlog.service.RoleService;
import br.com.spring.codeBlog.service.UsuarioService;

@Controller
public class UsuarioController {

	@Autowired
	UsuarioService usuarioService;

	@Autowired
	RoleService roleService;

	Usuario usuarioLogado;

	@Autowired
	Usuario usuarioPerfil;
	
	@Autowired
	PostagemService postagemService;

	@RequestMapping(value = "/cadastro", method = RequestMethod.POST)
	public String cadastrarUsuario(@Valid Usuario usuario, BindingResult result,
			RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			redirectAttributes.addFlashAttribute("mensagem", "Verifique se os campos obrigatórios foram preenchidos!");
			return "redirect:/";
		}

		Role role = roleService.findByRole("User");
		usuario.setListaRoles(Arrays.asList(role));

		usuarioService.save(usuario);
		return "redirect:/posts";
	}

	@RequestMapping(value = "/cadastro", method = RequestMethod.GET)
	public String getCadastroPage() {
		return "cadastro";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginPage() {
		return "login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public void getUser() {

	}

	@RequestMapping(value = "/perfil/{username}", method = RequestMethod.GET)
	public ModelAndView getPerfil(@PathVariable("username") String username) {
		ModelAndView mv = new ModelAndView("perfilUsuario");
		
		usuarioPerfil = usuarioService.findByUsername(username);
		
		//usuarioLogado = (Usuario) SecurityContextHolder.getContext()
		//		.getAuthentication().getPrincipal();
		
		usuarioLogado = usuarioService.findByUsername(SecurityContextHolder.getContext()
				.getAuthentication().getName());
		
		System.out.println("Nome do usuário logado: " + usuarioLogado.getNome());
		
		System.out.println("Contém usuário logado: " + usuarioPerfil.getListaSeguidores());
		
		List<Postagem> postsByUsuarioPerfil = postagemService.findAllByAutor(usuarioPerfil);
		
		mv.addObject("postsByUsuarioPerfil", postsByUsuarioPerfil);
		mv.addObject("usuarioPerfil", usuarioPerfil);
		mv.addObject("usuarioLogado", usuarioLogado);
		return mv;
	}

	@RequestMapping(value = "/meuPerfil")
	public String getMeuPerfil() {

		return "redirect:/perfil/" + SecurityContextHolder.getContext().getAuthentication().getName();
	}

	@RequestMapping(value = "/perfilUsuario/seguir", method = RequestMethod.GET)
	public String getSeguirUsuario() {
		usuarioLogado.getListaSeguindo().add(usuarioPerfil);
		usuarioPerfil.getListaSeguidores().add(usuarioLogado);
		
		usuarioService.save(usuarioLogado);
		usuarioService.save(usuarioPerfil);

		return "redirect:/perfil/" + usuarioPerfil.getUsername();

	}
	
	@RequestMapping(value = "/perfilUsuario/deixarDeSeguir", method = RequestMethod.GET)
	public String getDeixarDeSeguir() {
		usuarioLogado.getListaSeguindo().remove(usuarioPerfil);
		usuarioPerfil.getListaSeguidores().remove(usuarioLogado);
		
		usuarioService.save(usuarioLogado);
		usuarioService.save(usuarioPerfil);

		return "redirect:/perfil/" + usuarioPerfil.getUsername();

	}
}
