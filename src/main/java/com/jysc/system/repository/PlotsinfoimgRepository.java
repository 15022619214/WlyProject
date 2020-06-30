package com.jysc.system.repository;

import com.jysc.system.model.Plotsinfoimg;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PlotsinfoimgRepository extends CrudRepository<Plotsinfoimg, Integer>, JpaSpecificationExecutor<Plotsinfoimg> {
    public List<Plotsinfoimg> findByInfoid(Integer infoid);
}
