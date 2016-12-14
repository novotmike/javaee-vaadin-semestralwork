package cz.novotm60.model.dao;

import cz.novotm60.model.entity.ChangeOrder;
import cz.novotm60.model.entity.PhoneNumber;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.logging.Level;
import java.util.logging.Logger;

@ApplicationScoped
@Transactional
public class PhoneNumberDao {

    @Inject
    EntityManager entityManager;

    public boolean addNew(PhoneNumber entity) {
        try {
            entityManager.persist(entity);
            return true;
        }catch(Exception ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.INFO, ex.getMessage());
            return false;
        }
    }

    public boolean delete(PhoneNumber entity) {
        try {
            entityManager.remove(entityManager.contains(entity) ? entity : entityManager.merge(entity));
            return true;
        }catch(Exception ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.INFO, ex.getMessage());
            return false;
        }
    }
}
