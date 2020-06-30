package com.jysc.system.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

/*
 *  地块分类
 *
 */
@Entity
@Table(name = "plotsclassify")
public class Plotsclassify extends EntityId {

    @Column(name = "pcname", length = 255)
    private String pcname;

    @Column(name = "po_id", length = 255)
    private Integer poid;

    public String getPcname() {
        return pcname;
    }

    public void setPcname(String pcname) {
        this.pcname = pcname;
    }

    public Integer getPoid() {
        return poid;
    }

    public void setPoid(Integer poid) {
        this.poid = poid;
    }
}
