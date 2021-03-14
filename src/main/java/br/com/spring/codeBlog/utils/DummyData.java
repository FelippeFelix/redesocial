package br.com.spring.codeBlog.utils;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import br.com.spring.codeBlog.model.Postagem;
import br.com.spring.codeBlog.model.Role;
import br.com.spring.codeBlog.model.Usuario;
import br.com.spring.codeBlog.repository.RoleRepository;
import br.com.spring.codeBlog.repository.UsuarioRepository;
import br.com.spring.codeBlog.service.PostagemService;
import br.com.spring.codeBlog.service.RoleService;
import br.com.spring.codeBlog.service.UsuarioService;

@Component
public class DummyData {

	@Autowired
	UsuarioService usuarioService;

	@Autowired
	RoleService roleService;
	
	@Autowired
	PostagemService postagemService;
	
    @PostConstruct
    public void savePosts(){
    	
    	//criarRoles();
    	//criarNovosUsuarios();
    	//criarNovosPosts();
		
    }
    
    public void criarNovosUsuarios() {
		Role role = roleService.findByRole("ADMIN");
    	
		Usuario usuarioAdmin = new Usuario();

		usuarioAdmin.setNome("Felipe Felix Pessoa");
		usuarioAdmin.setPassword("27867169");
		usuarioAdmin.setEmail("felippe.felix98@gmail.com");
		usuarioAdmin.setUsername("felipemugen");
		usuarioAdmin.setListaRoles(Arrays.asList(role));

		usuarioService.save(usuarioAdmin);

		Usuario usuarioNormal = new Usuario();

		usuarioNormal.setNome("Maria Silva");
		usuarioNormal.setPassword("123456");
		usuarioNormal.setEmail("maria.silva@gmail.com");
		usuarioNormal.setUsername("mariasilva");
		usuarioNormal.setListaRoles(Arrays.asList(role));
		

		usuarioService.save(usuarioNormal);
    }
    
    public void criarRoles() {
    	Role roleAdmin = new Role();
    	roleAdmin.setRole("ADMIN");
    	
		Role roleUser = new Role();
		roleUser.setRole("USER");
		
		roleService.save(roleAdmin);
		roleService.save(roleUser);
    }
    
    public void criarNovosPosts() {
    	Usuario usuario = usuarioService.findById(5);
    	
    	
        Postagem post1 = new Postagem();
        post1.setAutor(usuario);
        post1.setData(LocalDate.now());
        post1.setTitulo("Docker");
        post1.setTexto("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.");

        
        postagemService.save(post1);
    	
    }
}
