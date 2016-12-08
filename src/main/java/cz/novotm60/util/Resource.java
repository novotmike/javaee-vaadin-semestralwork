package cz.novotm60.util;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

//@ApplicationScoped
public class Resource {

    //@PersistenceContext
    private EntityManager em;

    //@Produces
    public EntityManager getEntityManager() {
        return em;
    }
}
