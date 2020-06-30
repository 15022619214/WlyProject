package com.jysc.system.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/*
 *  地块信息
 *
 */
@Entity
@Table(name = "plotsinfonav")
public class Plotsinfonav extends EntityId {

    @Column(name = "plots_id", length = 255)
    private Integer plotsid; //绑定地块列表id

    @Column(name = "UperMapCheack", length = 255)
    private String UperMapCheack;

    @Column(name = "PlotsYsCheack", length = 255)
    private String PlotsYsCheack;
    
    @Column(name = "PlotsJsCheack", length = 255)
    private String PlotsJsCheack;

    @Column(name = "ServiceCheack", length = 255)
    private String ServiceCheack;

    @Column(name = "MapPainCheack", length = 255)
    private String MapPainCheack;

    @Column(name = "LetourCheack", length = 255)
    private String LetourCheack;

    public Integer getPlotsid() {
        return plotsid;
    }

    public void setPlotsid(Integer plotsid) {
        this.plotsid = plotsid;
    }

    public String getUperMapCheack() {
        return UperMapCheack;
    }

    public void setUperMapCheack(String uperMapCheack) {
        UperMapCheack = uperMapCheack;
    }

    public String getPlotsYsCheack() {
        return PlotsYsCheack;
    }

    public void setPlotsYsCheack(String plotsYsCheack) {
        PlotsYsCheack = plotsYsCheack;
    }

    public String getPlotsJsCheack() {
        return PlotsJsCheack;
    }

    public void setPlotsJsCheack(String plotsJsCheack) {
        PlotsJsCheack = plotsJsCheack;
    }

    public String getServiceCheack() {
        return ServiceCheack;
    }

    public void setServiceCheack(String serviceCheack) {
        ServiceCheack = serviceCheack;
    }

    public String getMapPainCheack() {
        return MapPainCheack;
    }

    public void setMapPainCheack(String mapPainCheack) {
        MapPainCheack = mapPainCheack;
    }

    public String getLetourCheack() {
        return LetourCheack;
    }

    public void setLetourCheack(String letourCheack) {
        LetourCheack = letourCheack;
    }
}
