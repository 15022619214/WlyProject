package com.jysc.system.service.serviceImpl;

import com.jysc.system.model.Plotsinfoservice;
import com.jysc.system.repository.PlotsinfoserviceRepository;
import com.jysc.system.service.PlotsinfoserviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlotsinfoserviceServiceImpl implements PlotsinfoserviceService {

    @Autowired
    PlotsinfoserviceRepository plotsinfoserviceRepository;


    @Override
    public List<Plotsinfoservice> findByPlotsid(Integer plotsid) {
        return this.plotsinfoserviceRepository.findByPlotsid(plotsid);
    }

    @Override
    public Plotsinfoservice findById(Integer id) {
        return this.plotsinfoserviceRepository.findById(id).get();
    }

    @Override
    public List<Plotsinfoservice> findByPlotsidAndSertype(Integer plotsid, String sertype) {
        return this.plotsinfoserviceRepository.findByPlotsidAndSertype(plotsid, sertype);
    }

    @Override
    public Plotsinfoservice save(Plotsinfoservice plotsinfoservice) {
        return this.plotsinfoserviceRepository.save(plotsinfoservice);
    }

    @Override
    public void del(Integer id) {
        this.plotsinfoserviceRepository.deleteById(id);
    }
}
