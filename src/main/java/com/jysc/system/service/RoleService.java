package com.jysc.system.service;

import java.util.List;

import com.jysc.system.model.Role;

public interface RoleService {
	public List<Role> findAll();
	public Role save(Role role);
	public void delete(Integer id);
	 public List<Role> findByRolename(String rolename);
	public Role findOne(Integer id);
	public Integer containsRole(Integer id);
}
