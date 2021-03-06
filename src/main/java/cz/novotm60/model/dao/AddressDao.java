package cz.novotm60.model.dao;

import cz.novotm60.model.entity.Address;
import cz.novotm60.model.entity.PhoneNumber;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.logging.Level;
import java.util.logging.Logger;

@ApplicationScoped
@Transactional
public class AddressDao {

    @Inject
    EntityManager entityManager;

    public boolean addNew(Address entity) {
        try {
            entityManager.persist(entity);
            return true;
        }catch(Exception ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.INFO, ex.getMessage());
            return false;
        }
    }

    public boolean delete(Address entity) {
        try {
            entityManager.remove(entityManager.contains(entity) ? entity : entityManager.merge(entity));
            return true;
        }catch(Exception ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.INFO, ex.getMessage());
            return false;
        }
    }
}
