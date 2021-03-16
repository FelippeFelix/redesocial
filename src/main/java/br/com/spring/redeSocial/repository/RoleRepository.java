package br.com.spring.redeSocial.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.spring.redeSocial.model.Role;

public interface RoleRepository extends CrudRepository<Role, Integer>{
	Role findByRole(String role);
	
}
