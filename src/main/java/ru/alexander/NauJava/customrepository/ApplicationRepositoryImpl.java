package ru.alexander.NauJava.customrepository;

import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.alexander.NauJava.entity.Application;
import ru.alexander.NauJava.entity.User;

import java.util.List;

@Repository
public class ApplicationRepositoryImpl implements ApplicationRepositoryCustom {
    private final EntityManager entityManager;

    @Autowired
    public ApplicationRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Application> findAllByUser(User user) {
        var criteriaBuilder = entityManager.getCriteriaBuilder();
        var criteriaQuery = criteriaBuilder.createQuery(Application.class);
        var applicationRoot = criteriaQuery.from(Application.class);
        criteriaQuery.select(applicationRoot)
                .where(criteriaBuilder.equal(applicationRoot.get("user"), user));
        return entityManager.createQuery(criteriaQuery).getResultList();
    }
}
