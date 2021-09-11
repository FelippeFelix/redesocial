package br.com.spring.redesocial.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.spring.redesocial.model.Role;

public interface RoleRepository extends CrudRepository<Role, Integer>{
	Role findByRole(String role);
	
}
