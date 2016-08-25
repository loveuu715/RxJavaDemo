package com.vv.mydesignframework.injector.module;

import android.content.Context;

import com.vv.mydesignframework.db.DaoMaster;
import com.vv.mydesignframework.db.DaoSession;
import com.vv.mydesignframework.db.DemoDBBeanDao;
import com.vv.mydesignframework.db.ThreadInfoDao;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by VV on 2016/8/24.
 */
@Module
public class DBModule {

    private final String DB_NAME = "myDF.db";

    @Provides
    @Singleton
    DaoMaster.DevOpenHelper provideDevOpenHelper(Context context){
        return new DaoMaster.DevOpenHelper(context, DB_NAME, null);
    }

    @Provides
    @Singleton
    DaoMaster provideDaoMasterW(DaoMaster.DevOpenHelper helper) {
        return new DaoMaster(helper.getWritableDatabase());
    }

    @Provides
    @Singleton
    DaoMaster provideDaoMasterR(DaoMaster.DevOpenHelper helper) {
        return new DaoMaster(helper.getReadableDatabase());
    }

    @Provides
    @Singleton
    DaoSession provideDaoSession(DaoMaster master) {
        return master.newSession();
    }

    @Provides
    @Singleton
    DemoDBBeanDao provideDemoDBBeanDao(DaoSession session) {
        return session.getDemoDBBeanDao();
    }

    @Provides
    @Singleton
    ThreadInfoDao provideThreadInfoDao(DaoSession session) {
        return session.getThreadInfoDao();
    }

}
