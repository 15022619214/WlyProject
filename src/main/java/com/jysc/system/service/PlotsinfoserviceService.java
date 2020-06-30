package com.jysc.system.service;

import com.jysc.system.model.Plotsinfoservice;

import java.util.List;

public interface PlotsinfoserviceService {
    public List<Plotsinfoservice> findByPlotsid(Integer plotsid);
    public Plotsinfoservice findById(Integer id);
    public List<Plotsinfoservice> findByPlotsidAndSertype(Integer plotsid, String sertype);
	public Plotsinfoservice save(Plotsinfoservice plotsinfoservice);
	public void del(Integer id);
}
