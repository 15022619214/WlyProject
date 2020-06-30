package com.jysc.system.service;

import com.jysc.system.model.Plotsinfo;

public interface PlotsinfoService {
	public Plotsinfo findByPlotsid(Integer plotsid);
	public Plotsinfo save(Plotsinfo plotsinfo);
	public void del(Integer id);
}
