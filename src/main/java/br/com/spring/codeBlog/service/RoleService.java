package br.com.spring.codeBlog.service;

import br.com.spring.codeBlog.model.Role;

public interface RoleService {
	Role findByRole(String role);
	Role save(Role role);
}
