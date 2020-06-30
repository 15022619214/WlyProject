package com.jysc.system.service.serviceImpl;

import com.jysc.system.model.Plotsinfoimg;
import com.jysc.system.repository.PlotsinfoimgRepository;
import com.jysc.system.service.PlotsinfoimgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlotsinfoimgServiceImpl implements PlotsinfoimgService {

	@Autowired
	PlotsinfoimgRepository PlotsinfoimgRepository;


	@Override
	public List<Plotsinfoimg> findByInfoid(Integer infoid) {
		return this.PlotsinfoimgRepository.findByInfoid(infoid);
	}

	@Override
	public Plotsinfoimg save(Plotsinfoimg plotsinfoimg) {
		return this.PlotsinfoimgRepository.save(plotsinfoimg);
	}

	@Override
	public void del(Integer id) {
		this.PlotsinfoimgRepository.deleteById(id);
	}
}
