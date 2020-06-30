package com.jysc.system.repository;

import com.jysc.system.model.Plotsclassify;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PlotsclassifyRepository extends CrudRepository<Plotsclassify, Integer>, JpaSpecificationExecutor<Plotsclassify> {
    public List<Plotsclassify> findByPoid(Integer poid);
    public Plotsclassify findByPcnameAndPoid(String pcname,Integer poid);
}
