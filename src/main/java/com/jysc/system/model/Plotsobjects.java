package com.jysc.system.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/*
 *  地块项目
 *
 */
@Entity
@Table(name = "plotsobjects")
public class Plotsobjects extends EntityId {

    @Column(name = "po_name", length = 255)
    private String poname;

    @Column(name = "po_imgurl", length = 2000)
    private String poimgurl;

    @Column(name = "po_logo_imgurl", length = 2000)
    private String po_logo_imgurl;

    @Column(name = "isOpen", length = 255)
    private Integer isOpen;

    public String getPoname() {
        return poname;
    }

    public void setPoname(String poname) {
        this.poname = poname;
    }

    public Integer getIsOpen() {
        return isOpen;
    }

    public void setIsOpen(Integer isOpen) {
        this.isOpen = isOpen;
    }

    public String getPoimgurl() {
        return poimgurl;
    }

    public void setPoimgurl(String poimgurl) {
        this.poimgurl = poimgurl;
    }

    public String getPo_logo_imgurl() {
        return po_logo_imgurl;
    }

    public void setPo_logo_imgurl(String po_logo_imgurl) {
        this.po_logo_imgurl = po_logo_imgurl;
    }
}
