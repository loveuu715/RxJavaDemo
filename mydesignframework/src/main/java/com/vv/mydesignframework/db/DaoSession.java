package com.vv.mydesignframework.db;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.vv.mydesignframework.db.DemoDBBean;
import com.vv.mydesignframework.db.ThreadInfo;

import com.vv.mydesignframework.db.DemoDBBeanDao;
import com.vv.mydesignframework.db.ThreadInfoDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig demoDBBeanDaoConfig;
    private final DaoConfig threadInfoDaoConfig;

    private final DemoDBBeanDao demoDBBeanDao;
    private final ThreadInfoDao threadInfoDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        demoDBBeanDaoConfig = daoConfigMap.get(DemoDBBeanDao.class).clone();
        demoDBBeanDaoConfig.initIdentityScope(type);

        threadInfoDaoConfig = daoConfigMap.get(ThreadInfoDao.class).clone();
        threadInfoDaoConfig.initIdentityScope(type);

        demoDBBeanDao = new DemoDBBeanDao(demoDBBeanDaoConfig, this);
        threadInfoDao = new ThreadInfoDao(threadInfoDaoConfig, this);

        registerDao(DemoDBBean.class, demoDBBeanDao);
        registerDao(ThreadInfo.class, threadInfoDao);
    }
    
    public void clear() {
        demoDBBeanDaoConfig.getIdentityScope().clear();
        threadInfoDaoConfig.getIdentityScope().clear();
    }

    public DemoDBBeanDao getDemoDBBeanDao() {
        return demoDBBeanDao;
    }

    public ThreadInfoDao getThreadInfoDao() {
        return threadInfoDao;
    }

}