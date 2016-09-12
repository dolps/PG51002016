package service;

import model.User;
import repository.UserRepository;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

/**
 * Created by dolplads on 07/09/16.
 */
@Stateless
public class UserEJB extends TransactionAble implements UserRepository {
    private EntityManager entityManager;

    public UserEJB(EntityManager entityManager) {
        super(entityManager);
        this.entityManager = entityManager;
    }

    @Override
    public Set<String> getCountriesUsersRepresent() {
        return null;
    }

    @Override
    public int numberOfUsers() {
        Long result = (Long) entityManager.createNamedQuery(User.GET_NUMBER_OF_USERS).getSingleResult();
        return Math.toIntExact(result);
    }

    @Override
    public int numberOfUsersByCountry(String countryName) {
        Long result = (Long) entityManager
                .createNamedQuery(User.GET_NUMBER_OF_USERS_FROM_COUNTRY)
                .setParameter("cName", countryName).getSingleResult();

        return Math.toIntExact(result);
    }

    @Override
    public List<User> mostActiveBasedOnPosts(int limit) {
        @SuppressWarnings(value = "unchecked")
        List<User> users = entityManager
                .createNamedQuery(User.MOST_ACTIVE_POSTER)
                .setMaxResults(limit).getResultList();
        return users;
    }

    @Override
    public List<User> mostActiveBasedOnComments(int limit) {
        @SuppressWarnings(value = "unchecked")
        List<User> users = entityManager
                .createNamedQuery(User.MOST_ACTIVE_COMMENTER)
                .setMaxResults(limit).getResultList();
        return users;
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
}
