package dao;

import model.Cat;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import util.SessionUtil;

import java.util.List;

public class CatDaoImpl implements DAO<Cat> {

    SessionUtil sessionUtil = new SessionUtil();

    @Override
    public void create(Cat entity) {
        sessionUtil.openTransactionSession();
        Session session = sessionUtil.getSession();
        session.save(entity);
        sessionUtil.closeTransactionSession();
    }

    @Override
    public void update(Cat entity) {
        sessionUtil.openTransactionSession();
        Session session = sessionUtil.getSession();
        session.saveOrUpdate(entity);
        sessionUtil.closeTransactionSession();
    }

    @Override
    public Cat findById(String id) {
        sessionUtil.openTransactionSession();
        String      sql         = "SELECT * FROM cats WHERE ID = ?1";
        NativeQuery nativeQuery = sessionUtil.getSession().createNativeQuery(sql);
        NativeQuery query       = nativeQuery.addEntity(Cat.class);
        query.setParameter(1,id);
        List<Cat> cats  = query.getResultList();
        sessionUtil.closeTransactionSession();
        if(cats == null || cats.isEmpty()){
            return null;
        }
        return cats.get(0);
    }

    @Override
    public void delete(Cat entity) {
        sessionUtil.openTransactionSession();
        sessionUtil.getSession().remove(entity);
        sessionUtil.closeTransactionSession();
    }

    @Override
    public List<Cat> findAll() {
        sessionUtil.openTransactionSession();
        String    sql   = "SELECT * FROM cats";
        Query     query = sessionUtil.getSession().createNativeQuery(sql).addEntity(Cat.class);
        List<Cat> cats  = query.getResultList();
        sessionUtil.closeTransactionSession();
        return cats;
    }
}
