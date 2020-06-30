package com.jysc.system.service;

import com.jysc.system.model.Plotsinfoimg;

import java.util.List;

public interface PlotsinfoimgService {
	public List<Plotsinfoimg> findByInfoid(Integer infoid);
	public Plotsinfoimg save(Plotsinfoimg plotsinfoimg);
	public void del(Integer id);
}
