package com.vv.mydesignframework.db;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by VV on 2016/8/24.
 */
@Entity
public class ThreadInfo {
    @Id(autoincrement = true)
    private long id;
    private String title;
    private long finished;
    private int status;
    private long size;
    public long getSize() {
        return this.size;
    }
    public void setSize(long size) {
        this.size = size;
    }
    public int getStatus() {
        return this.status;
    }
    public void setStatus(int status) {
        this.status = status;
    }
    public long getFinished() {
        return this.finished;
    }
    public void setFinished(long finished) {
        this.finished = finished;
    }
    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public long getId() {
        return this.id;
    }
    public void setId(long id) {
        this.id = id;
    }
    @Generated(hash = 699373533)
    public ThreadInfo(long id, String title, long finished, int status, long size) {
        this.id = id;
        this.title = title;
        this.finished = finished;
        this.status = status;
        this.size = size;
    }
    @Generated(hash = 930225280)
    public ThreadInfo() {
    }

}
