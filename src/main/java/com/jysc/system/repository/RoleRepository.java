package com.jysc.system.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.jysc.system.model.Role;

public interface RoleRepository extends CrudRepository<Role,Integer>,JpaSpecificationExecutor<Role> {
    public List<Role> findByRolename(String rolename);
    public List<Role> findAll();
	public Role save(Role role);
	
	@Query(value = "select distinct(roles_id) from user_roles where  roles_id=?1",nativeQuery = true)
	public Integer containsRole(Integer id);

	
}
