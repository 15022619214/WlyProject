package com.jysc.system.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import com.jysc.system.model.Plotsinfo;

public interface PlotsinfoRepository extends CrudRepository<Plotsinfo, Integer>, JpaSpecificationExecutor<Plotsinfo> {
    public Plotsinfo findByPlotsid(Integer plotsid);
}
