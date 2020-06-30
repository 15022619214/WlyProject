package com.jysc.system.controller;

import com.jysc.system.model.Plotsinfoimg;
import com.jysc.system.service.*;
import com.jysc.system.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;


@Controller
public abstract class BaseController {

    @Autowired
    protected UserService userService;

    @Autowired
    protected RoleService roleService;

    @Autowired
    protected SecurityUtil securityUtil;
    
    @Autowired
    protected PlotsinfoService plotsinfoService;

    @Autowired
    protected PlotsService plotsService;

    @Autowired
    protected PlotsclassifyService plotsclassifyService;

    @Autowired
    protected PlotsobjectsService plotsobjectsService;

    @Autowired
    protected PlotsinfomapService plotsinfomapService;

    @Autowired
    protected PlotsinfoimgService plotsinfoimgService;

    @Autowired
    protected PlotsinfoserviceService plotsinfoserviceService;

    @Autowired
    protected PlotsinfonavService plotsinfonavService;
}
