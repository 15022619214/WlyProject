package com.jysc.system.repository;

import com.jysc.system.model.Plotsinfoservice;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PlotsinfoserviceRepository extends CrudRepository<Plotsinfoservice, Integer>, JpaSpecificationExecutor<Plotsinfoservice> {
    public List<Plotsinfoservice> findByPlotsid(Integer plotsid);
    public List<Plotsinfoservice> findByPlotsidAndSertype(Integer plotsid, String sertype);
}
