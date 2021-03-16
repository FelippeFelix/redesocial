package br.com.spring.redeSocial.service;

import br.com.spring.redeSocial.model.Role;

public interface RoleService {
	Role findByRole(String role);
	Role save(Role role);
}
