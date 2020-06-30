package com.jysc.system.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jysc.system.model.Plotsclassify;
import com.jysc.system.repository.PlotsclassifyRepository;
import com.jysc.system.service.PlotsclassifyService;

@Service
public class PlotsclassifyServiceImpl implements PlotsclassifyService {

    @Autowired
    PlotsclassifyRepository plotsclassifyRepository;

    @Override
    public List<Plotsclassify> findByPoid(Integer poid) {
        return this.plotsclassifyRepository.findByPoid(poid);
    }

	@Override
	public void del(Integer id) {
		this.plotsclassifyRepository.deleteById(id);
	}

	@Override
	public Plotsclassify save(Plotsclassify plotsclassify) {
		return this.plotsclassifyRepository.save(plotsclassify);
	}

	@Override
	public Plotsclassify findById(Integer id) {
		return this.plotsclassifyRepository.findById(id).get();
	}

    @Override
    public Plotsclassify findByPcname(String pcname) {
        return this.plotsclassifyRepository.findByPcname(pcname);
    }
}
