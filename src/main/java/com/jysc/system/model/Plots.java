package com.jysc.system.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/*
 *  地块列表
 *
 */
@Entity
@Table(name = "plots")
public class Plots extends EntityId {

    @Column(name = "plots", length = 255)
    private String plots;//用户登录名

    @Column(name = "pc_id", length = 255)
    private Integer pcid;

    @Column(name = "user_id", length = 255)
    private Integer userid;

    @Column(name = "ser_name", length = 1000)
    private String sername;

    public String getPlots() {
        return plots;
    }

    public void setPlots(String plots) {
        this.plots = plots;
    }

    public Integer getPcid() {
        return pcid;
    }

    public void setPcid(Integer pcid) {
        this.pcid = pcid;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getSername() {
        return sername;
    }

    public void setSername(String sername) {
        this.sername = sername;
    }
}
