package service;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

/**
 * Created by dolplads on 08/09/16.
 */
public class TransactionAble {
    public EntityManager entityManager;

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
