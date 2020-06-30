package com.jysc.system.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jysc.system.model.Role;
import com.jysc.system.repository.RoleRepository;
import com.jysc.system.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService{

	@Autowired
	RoleRepository roleRepository;
	@Override
	public List<Role> findAll() {
		return this.roleRepository.findAll();
	}

	@Override
	public Role save(Role role) {
		return this.roleRepository.save(role);
	}

	@Override
	public void delete(Integer id) {
		this.roleRepository.deleteById(id);
	}

	@Override
	public List<Role> findByRolename(String rolename) {
		return (List<Role>) this.roleRepository.findByRolename(rolename);
	}

	@Override
	public Role findOne(Integer id) {
		return this.roleRepository.findById(id).get();
	}

	@Override
	public Integer containsRole(Integer id) {
		return this.roleRepository.containsRole(id);
	}

}
