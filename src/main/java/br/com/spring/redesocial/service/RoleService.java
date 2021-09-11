package br.com.spring.redesocial.service;

import br.com.spring.redesocial.model.Role;

public interface RoleService {
	Role findByRole(String role);
	Role save(Role role);
}
