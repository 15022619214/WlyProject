package com.jysc.system.repository;

import com.jysc.system.model.Plotsobjects;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

public interface PlotsobjectsRepository extends CrudRepository<Plotsobjects, Integer>, JpaSpecificationExecutor<Plotsobjects> {
    public List<Plotsobjects> findByIsOpen(Integer isopen);
}
