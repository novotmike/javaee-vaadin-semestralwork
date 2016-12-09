package cz.novotm60.util;

import cz.novotm60.model.entity.ChangeOrder;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@ApplicationScoped
public class Resource {

    @PersistenceContext
    @Produces
    private EntityManager em;

    public void hmm() {

    }
}
