package org.merariway.saleswarehouse.dao.auth;

import jakarta.persistence.EntityManager;
import org.merariway.saleswarehouse.entity.UserEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class AuthFirebaseRepositoryImpl implements AuthFirebaseRepository {

    private final EntityManager em;

    public AuthFirebaseRepositoryImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    @Transactional
    public void registerEmail(UserEntity user) {
        em.persist(user);
    }
}
