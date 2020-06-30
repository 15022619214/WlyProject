package com.jysc.system.service;

import com.jysc.system.model.Plotsobjects;

import java.util.List;
import java.util.Map;

public interface PlotsobjectsService {
    public List<Plotsobjects> findByIsOpen(Integer isopen);
    public List<Plotsobjects> findAll();
    public Plotsobjects save(Plotsobjects plotsobjects);
    public void del(Integer id);
    public Plotsobjects findById(Integer id);
    public List<Plotsobjects> query(Map<String, Object> map);
}
