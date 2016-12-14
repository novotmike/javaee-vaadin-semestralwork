package cz.novotm60.model.dao;

import cz.novotm60.model.entity.Customer;
import cz.novotm60.model.entity.User;

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
public class CustomerDao {

    @Inject
    EntityManager entityManager;


    public boolean addNew(Customer entity) {
        try {
            entityManager.persist(entity);
            return true;
        }catch(Exception ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.INFO, ex.getMessage());
            return false;
        }
    }

    public boolean delete(Customer entity) {
        try {
            entityManager.remove(entityManager.contains(entity) ? entity : entityManager.merge(entity));
            return true;
        }catch(Exception ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.INFO, ex.getMessage());
            return false;
        }
    }

    public Collection<Customer> getAll() {
        CriteriaQuery q = entityManager.getCriteriaBuilder().createQuery(Customer.class);
        Root<Customer> abstractRoot = q.from(Customer.class);
        q.select(abstractRoot);

        Collection<Customer> collection = entityManager.createQuery(q).getResultList();
        return collection;
    }
}
