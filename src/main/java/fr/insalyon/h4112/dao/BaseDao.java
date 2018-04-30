package fr.insalyon.h4112.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by siyingjiang on 2018/4/28.
 */
public class BaseDao {

    @Resource
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    // 获取 Session，注意：没有使用 openSession() ,使用 getCurrentSession()才能被 Spring 管理
    public Session getSession(){
        return sessionFactory.getCurrentSession();
    }
}
