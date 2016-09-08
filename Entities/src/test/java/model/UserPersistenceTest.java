package model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.inject.Inject;
import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by dolplads on 05/09/16.
 */
public class UserPersistenceTest {

    private EntityManagerFactory entityManagerFactory;
    //@Inject
    //@PersistenceContext(unitName = "H2DB",type = PersistenceContextType.EXTENDED)
    private EntityManager entityManager;

    @Before
    public void setUp() throws Exception {
        entityManagerFactory = Persistence.createEntityManagerFactory("H2DB");
        entityManager = entityManagerFactory.createEntityManager();
    }

    @After
    public void tearDown() throws Exception {
        entityManager.close();
        entityManagerFactory.close();
    }

    @Test
    public void persistAUser() throws Exception {
        Address address = new Address("undelLia", "1234", "oslo", "Norway");
        User user = new User("name", "surname", address, "email");
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        try {
            entityManager.persist(user);
            transaction.commit();
        } catch (Exception e) {
            System.out.println("it crashed");
        }
    }

    @Test
    public void queryAUserVerifyEmbeddedAddress() throws Exception {
        Address address = new Address("undelLia", "1234", "oslo", "Sweden");
        User user = new User("name", "surname", address, "email");
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        try {
            entityManager.persist(user);
            transaction.commit();


        } catch (Exception e) {
            System.out.println("it crashed");
            fail("persistence failed");
        }
        entityManager.clear();
        entityManager.getTransaction().begin();
        List<User> users = entityManager.createQuery("from User").getResultList();
        entityManager.getTransaction().commit();

        users.forEach(u -> System.out.println(u.getName()));

        assertEquals(users.size(), 1);
        assertEquals(users.get(0).getAddress().getCityName(), "oslo");
    }

    @Test
    public void count_all_different_countries_JPQL() throws Exception {
        persistThreeUsers();


        int result = Math.toIntExact(entityManager.createNamedQuery("distinctCountryCount", Long.class).getSingleResult());

        assertEquals("there should be two different countries", 2, result);

    }

    private void persistThreeUsers() {
        Address address = new Address("undelLia", "1234", "oslo", "Sweden");
        User user = new User("name", "surname", address, "email");

        Address address2 = new Address("undelLia", "1234", "oslo", "Norway");
        User user2 = new User("name", "surname", address2, "email");

        Address address3 = new Address("undelLia", "1234", "oslo", "Norway");
        User user3 = new User("name", "surname", address3, "email");

        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            entityManager.persist(user);
            entityManager.persist(user2);
            entityManager.persist(user3);
            transaction.commit();
        } catch (Exception e) {
            System.out.println("it crashed");
            fail("persistence failed");
        }
        entityManager.clear();
    }

    @Test
    public void test_number_of_users_is_correct() throws Exception {
        persistThreeUsers();

        int nOfUsers = Math.toIntExact(entityManager.createNamedQuery("nOfUsers", Long.class).getSingleResult());
        assertEquals("should be three users", 3, nOfUsers);

    }

    @Test
    public void test_number_of_users_from_same_country() {
        persistThreeUsers();

        int result = Math.toIntExact(entityManager.createNamedQuery("nOfUsersByCountry", Long.class).setParameter("cName", "Norway").getSingleResult());
        assertEquals("should be three users", 2, result);

        @SuppressWarnings("unchecked")
        List<User> users = entityManager
                .createQuery("select u from User u where u.address =:country")
                .setParameter("country", "norway")
                .getResultList();

        entityManager.createQuery("select user from User user where user.address.countryName = ?1").setParameter(1, "lol");
    }

    /**
     *
     * testing the jpql
     *
     */
    // adddefaultData -> setter opp noen brukere
    // why not clear the cache???
    // se testcasewhen
}