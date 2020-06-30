package com.jysc.system.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/*
 *  地块信息
 *
 */
@Entity
@Table(name = "plotsinfoimg")
public class Plotsinfoimg extends EntityId {

    @Column(name = "info_id", length = 255)
    private Integer infoid; //绑定地块列表id

    @Column(name = "img_name", length = 2000)
    private String imgname;

    @Column(name = "img_url", length = 2000)
    private String imgurl;

    @Column(name = "img_index", length = 255)
    private Integer imgindex;

    public Integer getInfoid() {
        return infoid;
    }

    public void setInfoid(Integer infoid) {
        this.infoid = infoid;
    }

    public String getImgname() {
        return imgname;
    }

    public void setImgname(String imgname) {
        this.imgname = imgname;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public Integer getImgindex() {
        return imgindex;
    }

    public void setImgindex(Integer imgindex) {
        this.imgindex = imgindex;
    }
}
