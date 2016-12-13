package cz.novotm60.model.dao;

import cz.novotm60.model.entity.User;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
@Transactional
public class UserDao {

    @Inject
    EntityManager entityManager;

    public User getByUsername(String username) {
        CriteriaQuery q = entityManager.getCriteriaBuilder().createQuery(User.class);
        Root<User> abstractRoot = q.from(User.class);
        q.select(abstractRoot);
        ParameterExpression<String> p = entityManager.getCriteriaBuilder().parameter(String.class);

        q.where(entityManager.getCriteriaBuilder().equal(abstractRoot.get("username"), p));
        Query qq = entityManager.createQuery(q);
        List<User> resultList = qq.setParameter(p, username).getResultList();
        return (resultList.isEmpty()) ? null : resultList.get(0);
    }
}
