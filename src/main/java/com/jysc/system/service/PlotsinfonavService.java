package com.jysc.system.service;

import com.jysc.system.model.Plotsinfonav;

public interface PlotsinfonavService {
    public Plotsinfonav findByPlotsid(Integer plotsid);
	public Plotsinfonav save(Plotsinfonav plotsinfonav);
	public void del(Integer id);
}
