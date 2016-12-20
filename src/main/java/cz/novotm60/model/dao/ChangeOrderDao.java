package cz.novotm60.model.dao;

import cz.novotm60.model.entity.ChangeOrder;
import cz.novotm60.model.entity.Customer;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

@ApplicationScoped
@Transactional
public class ChangeOrderDao {

    @Inject
    EntityManager entityManager;

    public boolean addNew(ChangeOrder entity) {
        try {
            entityManager.persist(entity);
            return true;
        }catch(Exception ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.INFO, ex.getMessage());
            return false;
        }
    }

    public boolean update(ChangeOrder entity) {
        try {
            entityManager.merge(entity);
            return true;
        }catch(Exception ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.INFO, ex.getMessage());
            return false;
        }
    }

    public boolean delete(ChangeOrder entity) {
        try {
            entityManager.remove(entityManager.contains(entity) ? entity : entityManager.merge(entity));
            return true;
        }catch(Exception ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.INFO, ex.getMessage());
            return false;
        }
    }

    public Collection<ChangeOrder> getAll() {
        CriteriaQuery q = entityManager.getCriteriaBuilder().createQuery(ChangeOrder.class);
        Root<ChangeOrder> abstractRoot = q.from(ChangeOrder.class);
        q.select(abstractRoot);
        Collection<ChangeOrder> collection = entityManager.createQuery(q).getResultList();
        return collection;
    }
}
