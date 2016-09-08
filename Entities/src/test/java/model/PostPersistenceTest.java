package model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * Created by dolplads on 05/09/16.
 */
public class PostPersistenceTest {
    private EntityManagerFactory entityManagerFactory;
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
    public void testPostCreationPersists() throws Exception {
        Address address = new Address("undelLia", "1234", "Oslo", "Norway");
        User user = new User("thomas", "dolplads", address, "dolplads@gmail.com");
        Date date = new Date();
        Post post = new Post(user, "This is a very nice comment", date);

        EntityTransaction transaction = entityManager.getTransaction();

        transaction.begin();
        entityManager.persist(post);
        entityManager.persist(user);
        transaction.commit();

        assertTrue(entityManager.contains(post));
    }

    @Test
    public void total_number_of_posts_is_correct() {
        Address address = new Address("undelLia", "1234", "Oslo", "Norway");
        User user = new User("thomas", "dolplads", address, "dolplads@gmail.com");
        User user2 = new User("thomas", "dolplads", address, "dolplads@gmail.com");
        Date date = new Date();
        Post post1 = new Post(user, "This is a verys nice comment", date);
        Post post2 = new Post(user2, "This is a post", date);

        EntityTransaction transaction = entityManager.getTransaction();

        transaction.begin();
        entityManager.persist(post1);
        entityManager.persist(user);
        entityManager.persist(post2);
        entityManager.persist(user2);
        transaction.commit();

        assertTrue(post1.getId() != null);

        int result = Math.toIntExact(entityManager.createNamedQuery("nOfPosts", Long.class).getSingleResult());
        assertEquals("there should be two posts in the database", 2, result);
        int result2 = Math.toIntExact(entityManager.createNamedQuery("nOfPostsByCountry", Long.class).setParameter("cName", "Norway").getSingleResult());
        assertEquals("there should be two posts in the database from Norway", 2, result2);
    }
}