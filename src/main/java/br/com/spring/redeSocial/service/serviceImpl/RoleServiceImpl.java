package br.com.spring.redeSocial.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.spring.redeSocial.model.Role;
import br.com.spring.redeSocial.repository.RoleRepository;
import br.com.spring.redeSocial.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {
	
	@Autowired
	RoleRepository roleRepository;
	
	@Override
	public Role findByRole(String role) {
		return roleRepository.findByRole(role);
	}
	
	@Override
	public Role save(Role role) {
		return roleRepository.save(role);
	}
}
