package service;

import lombok.NoArgsConstructor;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;

/**
 * Created by dolplads on 08/09/16.
 */
@Stateless
@NoArgsConstructor
public abstract class TransactionAble {

    private EntityManager entityManager;


    public TransactionAble(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void commitTransaction() {
        EntityTransaction transaction = getTransaction();
        transaction.commit();
    }

    public void startTransaction() {
        EntityTransaction transaction = getTransaction();
        transaction.begin();
    }

    private EntityTransaction getTransaction() {
        if (entityManager != null) {
            return entityManager.getTransaction();
        }
        throw new NullPointerException("entityManager is null");
    }
}
