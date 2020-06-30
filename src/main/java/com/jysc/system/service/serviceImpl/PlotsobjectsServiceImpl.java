package com.jysc.system.service.serviceImpl;

import java.util.List;
import java.util.Map;

import com.jysc.base.persistence.DynamicSpecifications;
import com.jysc.base.persistence.SearchFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.jysc.system.model.Plotsobjects;
import com.jysc.system.repository.PlotsobjectsRepository;
import com.jysc.system.service.PlotsobjectsService;

@Service
public class PlotsobjectsServiceImpl implements PlotsobjectsService {

    @Autowired
    PlotsobjectsRepository plotsobjectsRepository;

    @Override
    public List<Plotsobjects> findByIsOpen(Integer isopen) {
        return this.plotsobjectsRepository.findByIsOpen(isopen);
    }

    @Override
    public List<Plotsobjects> findAll() {
        return (List<Plotsobjects>) this.plotsobjectsRepository.findAll();
    }

	@Override
	public Plotsobjects save(Plotsobjects plotsobjects) {
		return this.plotsobjectsRepository.save(plotsobjects);
	}

	@Override
	public void del(Integer id) {
		this.plotsobjectsRepository.deleteById(id);
	}

	@Override
	public Plotsobjects findById(Integer id) {
		return this.plotsobjectsRepository.findById(id).get();
	}

    @Override
    public List<Plotsobjects> query(Map<String, Object> map) {
        Sort sort = new Sort(Sort.Direction.ASC, "id");
        Specification<Plotsobjects> spec = specification(map);
        List<Plotsobjects> list = this.plotsobjectsRepository.findAll(spec, sort);
        return list;
    }

    private Specification<Plotsobjects> specification(Map<String, Object> map){
        Map<String, SearchFilter> filters = SearchFilter.parse(map);
        Specification<Plotsobjects> spec = DynamicSpecifications.bySearchFilter(filters.values(), Plotsobjects.class);
        return spec;
    }
}
