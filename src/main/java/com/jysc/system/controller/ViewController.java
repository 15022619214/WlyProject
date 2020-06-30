package com.jysc.system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
public class ViewController extends BaseController{

	@RequestMapping("/403")
	public String deny() {
		return "403";
	}
    /**
     *
     * index
     * @return
     */
    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping("/plotsinfo")
    public String plotsinfo() {
        return "plotsinfo";
    }


    @RequestMapping("/admin")
    public String login() {
        return "system/login";
    }

    @RequestMapping("/login")
    public String logins() {
        return "system/login";
    }

    @RequestMapping("/plotsadmin")
    public String plotsadmin() {
        return "system/plotsadmin";
    }
    
    @RequestMapping("/plotsinfoadmin")
    public String plotsinfoadmin() {
        return "system/plotsinfoadmin";
    }

    @RequestMapping("/plotsshare")
    public String plotsshare() {
        return "system/plotsshare";
    }

}
