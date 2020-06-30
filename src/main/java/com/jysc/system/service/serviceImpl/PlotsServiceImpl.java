package com.jysc.system.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jysc.system.model.Plots;
import com.jysc.system.repository.PlotsRepository;
import com.jysc.system.service.PlotsService;

@Service
public class PlotsServiceImpl implements PlotsService {

    @Autowired
    PlotsRepository plotsRepository;

    @Override
    public List<Plots> findByPcid(Integer pcid) {
        return this.plotsRepository.findByPcid(pcid);
    }

    @Override
    public List<Plots> findByPcidAndUserid(Integer pcid, Integer userid) {
        return this.plotsRepository.findByPcidAndUserid(pcid, userid);
    }

    @Override
	public void del(Integer id) {
		this.plotsRepository.deleteById(id);
	}

	@Override
	public Plots save(Plots plots) {
		return this.plotsRepository.save(plots);
	}

	@Override
	public Plots findById(Integer id) {
		return this.plotsRepository.findById(id).get();
	}
}
