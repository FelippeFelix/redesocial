package br.com.spring.codeBlog.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.spring.codeBlog.model.Role;
import br.com.spring.codeBlog.model.Usuario;

public interface RoleRepository extends CrudRepository<Role, Integer>{
	Role findByRole(String role);
	
}
