package com.jysc.system.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/*
 *  地块信息
 *
 */
@Entity
@Table(name = "plotsinfomap")
public class Plotsinfomap extends EntityId {

    @Column(name = "info_id", length = 255)
    private Integer infoid; //绑定信息列表id

    @Column(name = "map_name", length = 2000)
    private String mapname;

    @Column(name = "map_url", length = 2000)
    private String mapurl;

    @Column(name = "map_index", length = 255)
    private Integer mapindex;

    public Integer getInfoid() {
        return infoid;
    }

    public void setInfoid(Integer infoid) {
        this.infoid = infoid;
    }

    public String getMapname() {
        return mapname;
    }

    public void setMapname(String mapname) {
        this.mapname = mapname;
    }

    public String getMapurl() {
        return mapurl;
    }

    public void setMapurl(String mapurl) {
        this.mapurl = mapurl;
    }

    public Integer getMapindex() {
        return mapindex;
    }

    public void setMapindex(Integer mapindex) {
        this.mapindex = mapindex;
    }
}
