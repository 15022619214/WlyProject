package com.jysc.system.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jysc.system.model.Plotsinfo;
import com.jysc.system.repository.PlotsinfoRepository;
import com.jysc.system.service.PlotsinfoService;

@Service
public class PlotsinfoServiceImpl implements PlotsinfoService {
	
	@Autowired
	PlotsinfoRepository plotsinfoRepository;

	@Override
	public Plotsinfo findByPlotsid(Integer plotsid) {
		return this.plotsinfoRepository.findByPlotsid(plotsid);
	}

	@Override
	public Plotsinfo save(Plotsinfo plotsinfo) {
		return this.plotsinfoRepository.save(plotsinfo);
	}

	@Override
	public void del(Integer id) {
		this.plotsinfoRepository.deleteById(id);
	}

   
}
