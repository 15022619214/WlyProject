package com.jysc.system.service.serviceImpl;

import com.jysc.system.model.Plotsinfomap;
import com.jysc.system.repository.PlotsinfomapRepository;
import com.jysc.system.service.PlotsinfomapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlotsinfomapServiceImpl implements PlotsinfomapService {

	@Autowired
	PlotsinfomapRepository PlotsinfomapRepository;

	@Override
	public List<Plotsinfomap> findByInfoid(Integer infoid) {
		return this.PlotsinfomapRepository.findByInfoid(infoid);
	}

	@Override
	public Plotsinfomap save(Plotsinfomap plotsinfomap) {
		return this.PlotsinfomapRepository.save(plotsinfomap);
	}

	@Override
	public void del(Integer id) {
		this.PlotsinfomapRepository.deleteById(id);
	}
}
