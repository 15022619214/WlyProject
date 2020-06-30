package com.jysc.system.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/*
 *  地块信息
 *
 */
@Entity
@Table(name = "plotsinfoservice")
public class Plotsinfoservice extends EntityId {

    @Column(name = "plots_id", length = 255)
    private Integer plotsid; //绑定地块列表id

    @Column(name = "ser_title", length = 2000)
    private String sertitle;

    @Column(name = "ser_url", length = 2000)
    private String serurl;

    @Column(name = "ser_type", length = 255)
    private String sertype;

    public Integer getPlotsid() {
        return plotsid;
    }

    public void setPlotsid(Integer plotsid) {
        this.plotsid = plotsid;
    }

    public String getSertitle() {
        return sertitle;
    }

    public void setSertitle(String sertitle) {
        this.sertitle = sertitle;
    }

    public String getSerurl() {
        return serurl;
    }

    public void setSerurl(String serurl) {
        this.serurl = serurl;
    }

    public String getSertype() {
        return sertype;
    }

    public void setSertype(String sertype) {
        this.sertype = sertype;
    }
}
