package com.vv.mydesignframework.db;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by VV on 2016/8/24.
 */
@Entity
public class DemoDBBean {
    @Id(autoincrement = true)
    private long id;
    private String name;
    private String desc;
    public String getDesc() {
        return this.desc;
    }
    public void setDesc(String desc) {
        this.desc = desc;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public long getId() {
        return this.id;
    }
    public void setId(long id) {
        this.id = id;
    }
    @Generated(hash = 2059291543)
    public DemoDBBean(long id, String name, String desc) {
        this.id = id;
        this.name = name;
        this.desc = desc;
    }
    @Generated(hash = 294613354)
    public DemoDBBean() {
    }
}
