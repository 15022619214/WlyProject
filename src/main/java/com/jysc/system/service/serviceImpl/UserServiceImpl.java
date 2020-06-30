package com.jysc.system.service.serviceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.jysc.base.persistence.DynamicSpecifications;
import com.jysc.base.persistence.SearchFilter;
import com.jysc.system.model.User;
import com.jysc.system.repository.UserRepository;
import com.jysc.system.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public void save(User user) {
        this.userRepository.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return this.userRepository.findByUsername(username);
    }

	@Override
	public List<User> findAll() {
		// TODO Auto-generated method stub
		return this.userRepository.findAll();
	}
	@Override
	 public List<User> query(Map<String, Object> searchParams, Sort sort){
		Specification<User> spec = buildSpecification(searchParams);
		return this.userRepository.findAll(spec, sort);
	}

    @Override
    public List<User> query(Map<String, Object> searchParams) {
        Specification<User> spec = bySearchFilter(searchParams);
        return this.userRepository.findAll(spec);
    }

    private Specification<User> bySearchFilter(Map<String, Object> searchParams){
        if (searchParams == null){
            searchParams = new HashMap<String, Object>();
        }
        Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
        Specification<User> spec = DynamicSpecifications.bySearchFilter(filters.values(), User.class);
        return spec;
    }

    private Specification<User> buildSpecification(
			Map<String, Object> searchParams) {
		Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
		Specification<User> spec = DynamicSpecifications.bySearchFilter(filters.values(), User.class);
		return spec;
	}

	private Pageable buildPageRequest(Integer pageNumber, Integer pageSize) {
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, sort);
        return pageable;
    }
	@Override
	public Page<User> query(Integer pageNumber, Integer pageSize,
			Map<String, Object> searchParams) {
		Pageable pageRequest = buildPageRequest(pageNumber, pageSize);
		Specification<User> spec = buildSpecification(searchParams);
		return this.userRepository.findAll(spec, pageRequest);
	}
	@Override
	public List<User> findBymysearchone(Integer roleid,String realname,String username) {
		return this.userRepository.findBymysearchone(roleid, realname,username);
	}

    @Override
    public User findById(Integer id) {
        return this.userRepository.findById(id).get();
    }

    @Override
    public void delete(Integer id) {
        this.userRepository.deleteById(id);
    }
}
