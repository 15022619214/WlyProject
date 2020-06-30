package com.jysc.system.repository;

import com.jysc.system.model.Plotsinfomap;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PlotsinfomapRepository extends CrudRepository<Plotsinfomap, Integer>, JpaSpecificationExecutor<Plotsinfomap> {
    public List<Plotsinfomap> findByInfoid(Integer infoid);
}
