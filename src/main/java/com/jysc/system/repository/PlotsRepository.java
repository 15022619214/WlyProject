package com.jysc.system.repository;

import com.jysc.system.model.Plots;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PlotsRepository extends CrudRepository<Plots, Integer>, JpaSpecificationExecutor<Plots> {
    public List<Plots> findByPcid(Integer pcid);
    public List<Plots> findByPcidAndUserid(Integer pcid, Integer userid);
}
