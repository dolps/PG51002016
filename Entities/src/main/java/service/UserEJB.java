package service;

import model.User;
import repository.UserRepository;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Set;

/**
 * Created by dolplads on 07/09/16.
 */
@Stateless
public class UserEJB implements UserRepository {
    //@PersistenceContext(unitName = "H2DB")
    private EntityManager entityManager;


    public UserEJB(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Set<String> getCountriesUsersRepresent() {
        return null;
    }

    @Override
    public int numberOfUsers() {
        return 0;
    }

    @Override
    public int numberOfUsersByCountry(String countryName) {
        return 0;
    }

    @Override
    public List<User> mostActiveBasedOnPosts(int limit) {
        return null;
    }

    @Override
    public List<User> mostActiveBasedOnComments(int limit) {
        return null;
    }

    @Override
    public User findById(Long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public void save(User entity) {
        entityManager.persist(entity);
    }

    @Override
    public void update(User entity) {
        entityManager.merge(entity);
    }

    @Override
    public void delete(User entity) {
        entityManager.remove(entityManager.merge(entity));
    }

    /**
     * can be used to retrieve the Transaction object
     * too handle storing of multiple objects
     *
     * @return
     */
    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public EntityTransaction getTransaction() {
        if (entityManager != null) {
            return entityManager.getTransaction();
        }
        throw new NullPointerException("entityManager is null");
    }

    public void commitTransaction() {
        EntityTransaction transaction = getTransaction();
        transaction.commit();
    }

    public void startTransaction() {
        EntityTransaction transaction = getTransaction();
        transaction.begin();
    }
}
