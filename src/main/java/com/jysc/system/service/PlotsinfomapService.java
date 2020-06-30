package com.jysc.system.service;

import com.jysc.system.model.Plotsinfomap;

import java.util.List;

public interface PlotsinfomapService {
	public List<Plotsinfomap> findByInfoid(Integer infoid);
	public Plotsinfomap save(Plotsinfomap plotsinfomap);
	public void del(Integer id);
}
