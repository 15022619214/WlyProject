package com.jysc.system.service;

import com.jysc.system.model.Plotsclassify;

import java.util.List;

public interface PlotsclassifyService {
    public List<Plotsclassify> findByPoid(Integer poid);
    public void del(Integer id);
    public Plotsclassify save(Plotsclassify plotsclassify);
    public Plotsclassify findById(Integer id);
    public Plotsclassify findByPcnameAndPoid(String pcname,Integer poid);
}
