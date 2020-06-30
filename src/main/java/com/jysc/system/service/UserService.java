package com.jysc.system.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import com.jysc.system.model.User;

public interface UserService {
    public void save(User user);
    public User findByUsername(String username);
    public List<User> findAll();
    public List<User> query(Map<String, Object> searchParams, Sort sort);
    public List<User> query(Map<String, Object> searchParams);
    public Page<User> query(Integer pageNumber, Integer pageSize,Map<String, Object> searchParams);
    public List<User> findBymysearchone(Integer roleid,String realname,String username);
    public User findById(Integer id);
    public void delete(Integer id);
}
