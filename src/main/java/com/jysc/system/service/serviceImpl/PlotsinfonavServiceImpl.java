package com.jysc.system.service.serviceImpl;

import com.jysc.system.model.Plotsinfonav;
import com.jysc.system.repository.PlotsinfonavRepository;
import com.jysc.system.service.PlotsinfonavService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlotsinfonavServiceImpl implements PlotsinfonavService {

    @Autowired
    PlotsinfonavRepository plotsinfonavRepository;

    @Override
    public Plotsinfonav findByPlotsid(Integer plotsid) {
        return this.plotsinfonavRepository.findByPlotsid(plotsid);
    }

    @Override
    public Plotsinfonav save(Plotsinfonav plotsinfonav) {
        return this.plotsinfonavRepository.save(plotsinfonav);
    }

    @Override
    public void del(Integer id) {
        this.plotsinfonavRepository.deleteById(id);
    }
}
