package com.jysc.system.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/*
 * 用户表
 * 		|用户地址维护 UserAddress
 */
@Entity
@Table(name = "user")
public class User extends EntityId implements UserDetails {

    @Column(name = "username", length = 255)
    private String username;//用户登录名

    @Column(name = "realname", length = 255)
    private String realname;//真实姓名

    @Column(name = "password", length = 255)
    private String password;//密码
    
    @Column(name = "isuse", length = 255)
    private Integer isuse;//0:启用；1：停用
    
    @Column(name = "phone", length = 255)
    private String phone;//手机号

    @ManyToMany(cascade = {CascadeType.REFRESH}, fetch = FetchType.EAGER)
    private List<Role> roles;//权限
  
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getIsuse() {
		return isuse;
	}

	public void setIsuse(Integer isuse) {
		this.isuse = isuse;
	}

	public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User() {
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> auth = new ArrayList<>();
        List<Role> roles = this.getRoles();
        for (Role role : roles) {
            auth.add(new SimpleGrantedAuthority(role.getRolename()));
        }
        return auth;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
