package dao;

import model.Cat;
import model.Owner;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import util.SessionUtil;

import java.util.List;

public class OwnerDaoImpl implements DAO<Owner> {

    SessionUtil sessionUtil = new SessionUtil();

    @Override
    public void create(Owner entity) {
        sessionUtil.openTransactionSession();
        Session session = sessionUtil.getSession();
        session.save(entity);
        sessionUtil.closeTransactionSession();
    }

    @Override
    public void update(Owner entity) {
        sessionUtil.openTransactionSession();
        Session session = sessionUtil.getSession();
        session.saveOrUpdate(entity);
        sessionUtil.closeTransactionSession();
    }

    @Override
    public Owner findById(String id) {
        sessionUtil.openTransactionSession();
        String      sql         = "SELECT * FROM owners WHERE ID = ?1";
        NativeQuery nativeQuery = sessionUtil.getSession().createNativeQuery(sql);
        NativeQuery query       = nativeQuery.addEntity(Owner.class);
        query.setParameter(1,id);
        List<Owner> owners  = query.getResultList();
        sessionUtil.closeTransactionSession();
        if(owners == null || owners.isEmpty()){
            return null;
        }
        return owners.get(0);
    }

    @Override
    public void delete(Owner entity) {
        sessionUtil.openTransactionSession();
        sessionUtil.getSession().remove(entity);
        sessionUtil.closeTransactionSession();
    }

    @Override
    public List<Owner> findAll() {
        sessionUtil.openTransactionSession();
        String    sql   = "SELECT * FROM owners";
        Query     query = sessionUtil.getSession().createNativeQuery(sql).addEntity(Owner.class);
        List<Owner> owners  = query.getResultList();
        sessionUtil.closeTransactionSession();
        return owners;
    }
}
