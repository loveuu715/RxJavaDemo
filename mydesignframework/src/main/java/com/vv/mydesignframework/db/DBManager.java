package com.vv.mydesignframework.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

/**
 * Created by VV on 2016/8/24.
 */
public class DBManager {
    private final static String dbName = "DBUSER_BEAN";
    private static volatile DBManager mInstance;
    private DaoMaster.DevOpenHelper openHelper;
    private Context context;

    private DBManager(Context context) {
        this.context = context;
        openHelper = new DaoMaster.DevOpenHelper(context, dbName, null);
    }

    public static DBManager getInstance(Context context) {
        if (mInstance == null) {
            synchronized (DBManager.class) {
                if (mInstance == null) {
                    mInstance = new DBManager(context);
                }
            }
        }
        return mInstance;
    }

    /**
     * 获取可读数据库
     */
    private SQLiteDatabase getReadableDatabase() {
        if (openHelper == null) {
            openHelper = new DaoMaster.DevOpenHelper(context, dbName, null);
        }
        SQLiteDatabase db = openHelper.getReadableDatabase();
        return db;
    }

    /**
     * 获取可写数据库
     */
    private SQLiteDatabase getWritableDatabase() {
        if (openHelper == null) {
            openHelper = new DaoMaster.DevOpenHelper(context, dbName, null);
        }
        SQLiteDatabase db = openHelper.getWritableDatabase();
        return db;
    }

    /**
     * 插入一条记录
     *
     * @param bean
     */
    public synchronized void insertUser(DemoDBBean bean) {
        DaoMaster daoMaster = new DaoMaster(getWritableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        DemoDBBeanDao userDao = daoSession.getDemoDBBeanDao();
        userDao.insertOrReplace(bean);
    }

    /**
     * 插入用户集合
     *
     * @param users
     */
    public synchronized void insertUserList(List<DemoDBBean> users) {
        if (users == null || users.isEmpty())
            return;
        DaoMaster daoMaster = new DaoMaster(getWritableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        DemoDBBeanDao userDao = daoSession.getDemoDBBeanDao();
        userDao.insertOrReplaceInTx(users);
    }

    /**
     * 删除一条记录
     *
     * @param user
     */
    public synchronized void deleteUser(DemoDBBean user) {
        DaoMaster daoMaster = new DaoMaster(getWritableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        DemoDBBeanDao userDao = daoSession.getDemoDBBeanDao();
        userDao.delete(user);
    }

    /**
     * 更新一条记录
     *
     * @param user
     */
    public synchronized void updateUser(DemoDBBean user) {
        DaoMaster daoMaster = new DaoMaster(getWritableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        DemoDBBeanDao userDao = daoSession.getDemoDBBeanDao();
        userDao.update(user);
    }

    /**
     * 查询所有用户列表
     */
    public List<DemoDBBean> queryUserList() {
        DaoMaster daoMaster = new DaoMaster(getReadableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        DemoDBBeanDao userDao = daoSession.getDemoDBBeanDao();
        QueryBuilder<DemoDBBean> qb = userDao.queryBuilder();
//        qb.orderDesc(DemoDBBeanDao.Properties.CreateTime); //排序
        return qb.list();
    }

    /**
     * 条件查询用户列表
     */
    public List<DemoDBBean> queryUserList(long userKey) {
        DaoMaster daoMaster = new DaoMaster(getReadableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        DemoDBBeanDao userDao = daoSession.getDemoDBBeanDao();
        QueryBuilder<DemoDBBean> qb = userDao.queryBuilder();
//        qb.orderDesc(DemoDBBeanDao.Properties.CreateTime); //排序
//        qb.where(DemoDBBeanDao.Properties.UserKey.gt(userKey));//查询条件
        List<DemoDBBean> list = qb.list();
        return list;
    }

    /**
     * 唯一查询
     *
     * @param userKey
     * @return
     */
    public DemoDBBean queryUser(String userKey) {
        DemoDBBean userBean = null;
        DaoMaster daoMaster = new DaoMaster(getReadableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        DemoDBBeanDao userDao = daoSession.getDemoDBBeanDao();
        QueryBuilder<DemoDBBean> qb = userDao.queryBuilder();
        userBean = qb.where(DemoDBBeanDao.Properties.Id.gt(userKey)).unique();
        return userBean;
    }

    /**
     * 分页查询 降序
     *
     * @param page
     * @return
     */
    public List<DemoDBBean> queryOffset(int page) {
        DaoMaster daoMaster = new DaoMaster(getReadableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        DemoDBBeanDao userDao = daoSession.getDemoDBBeanDao();
        QueryBuilder<DemoDBBean> qb = userDao.queryBuilder();
        qb.orderDesc(DemoDBBeanDao.Properties.Id);
        if (page > 0)
            qb.offset(5 * (page - 1));
        qb.limit(5);
        return qb.list();
    }
}
