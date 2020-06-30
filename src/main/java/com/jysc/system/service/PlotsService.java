package com.jysc.system.service;

import java.util.List;

import com.jysc.system.model.Plots;

public interface PlotsService {
    public List<Plots> findByPcid(Integer pcid);
    public List<Plots> findByPcidAndUserid(Integer pcid, Integer userid);
    public void del(Integer id);
    public Plots save(Plots plots);
    public Plots findById(Integer id);
}
