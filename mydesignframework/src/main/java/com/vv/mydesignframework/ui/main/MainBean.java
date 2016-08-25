package com.vv.mydesignframework.ui.main;

import javax.inject.Inject;

/**
 * Created by VV on 2016/8/24.
 */
public class MainBean {
    private String desc;

    @Inject
    public MainBean() {
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
