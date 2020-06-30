package com.jysc.system.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/*
 *  地块信息
 *
 */
@Entity
@Table(name = "plotsinfo")
public class Plotsinfo extends EntityId {

    @Column(name = "plots_id", length = 255)
    private Integer plotsid; //绑定地块列表id

    @Column(name = "plost_introduce", length = 2000)
    private String plostintroduce;//地块介绍

    @Column(name = "plost_tipimg", length = 2000)
    private String plosttipimg;//地块定位

    @Column(name = "plost_pain", length = 2000)
    private String plostpain;//地块定位
    
    @Column(name = "plost_painname", length = 500)
    private String plostpainname;//地块定位

    @Column(name = "plos_tletour", length = 2000)
    private String plostletour;//地块介绍


    public Integer getPlotsid() {
		return plotsid;
	}

	public void setPlotsid(Integer plotsid) {
		this.plotsid = plotsid;
	}

    public String getPlostintroduce() {
        return plostintroduce;
    }

    public void setPlostintroduce(String plostintroduce) {
        this.plostintroduce = plostintroduce;
    }

    public String getPlostpain() {
        return plostpain;
    }

    public void setPlostpain(String plostpain) {
        this.plostpain = plostpain;
    }

	public String getPlostpainname() {
		return plostpainname;
	}

	public void setPlostpainname(String plostpainname) {
		this.plostpainname = plostpainname;
	}

    public String getPlostletour() {
        return plostletour;
    }

    public void setPlostletour(String plostletour) {
        this.plostletour = plostletour;
    }

    public String getPlosttipimg() {
        return plosttipimg;
    }

    public void setPlosttipimg(String plosttipimg) {
        this.plosttipimg = plosttipimg;
    }
}
