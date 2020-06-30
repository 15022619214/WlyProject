package com.jysc.system.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.jysc.system.model.User;

public interface UserRepository extends CrudRepository<User,Integer>,JpaSpecificationExecutor<User> {
    public User findByUsername(String username);
    public List<User> findAll();
    
    @Modifying
    @Transactional
    @Query(value="SELECT * FROM user WHERE id in(SELECT user_id FROM user_roles WHERE if(?1!=-1, roles_id=?1,1=1) "
    		+ "and realname like ?2 and "
    		+ "if(?3!='', username=?3,1=1)) order by id desc",nativeQuery=true)
    public List<User> findBymysearchone(Integer roleid,String realname,String username);
   }
