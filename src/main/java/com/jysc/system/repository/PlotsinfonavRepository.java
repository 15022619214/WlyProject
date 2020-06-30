package com.jysc.system.repository;

import com.jysc.system.model.Plotsinfonav;
import com.jysc.system.model.Plotsinfoservice;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PlotsinfonavRepository extends CrudRepository<Plotsinfonav, Integer>, JpaSpecificationExecutor<Plotsinfonav> {
    public Plotsinfonav findByPlotsid(Integer plotsid);
}
