package com.jysc.system.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "role")
public class Role extends EntityId{

	/*权限名字*/
    @Column(name = "rolename",length = 255)
    private String rolename;

    /*权限描述*/
    @Column(name = "role_description",length = 255)
    private String roleDescription;

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

	public String getRoleDescription() {
		return roleDescription;
	}

	public void setRoleDescription(String roleDescription) {
		this.roleDescription = roleDescription;
	}
}
