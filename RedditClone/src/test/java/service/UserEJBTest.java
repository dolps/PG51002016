package service;

import model.Address;
import model.Post;
import model.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by dolplads on 08/09/16.
 */
public class UserEJBTest {
    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;
    private UserEJB userEJB;

    @Before
    public void setUp() throws Exception {
        this.entityManagerFactory = Persistence.createEntityManagerFactory("H2DB");
        this.entityManager = entityManagerFactory.createEntityManager();
        this.userEJB = new UserEJB(entityManager);
    }

    @After
    public void tearDown() throws Exception {
        entityManager.close();
        entityManagerFactory.close();
    }

    @Test
    public void getCountriesUsersRepresent() throws Exception {
        List<User> users = sampleUsers();

        userEJB.startTransaction();
        persistUsers(users);
        userEJB.commitTransaction();

        @SuppressWarnings(value = "unchecked")
        List<String> distinctCountries = entityManager.createNamedQuery(User.DISTINCT_COUNTRIES).getResultList();

        assertTrue(distinctCountries.stream().filter("norway"::equals).count() == 1);
        assertTrue(distinctCountries.stream().anyMatch("sweden"::equals));

    }

    @Test
    public void numberOfUsers() throws Exception {
        List<User> users = sampleUsers();

        userEJB.startTransaction();
        persistUsers(users);
        userEJB.commitTransaction();

        assertEquals(3, userEJB.numberOfUsers());

    }

    @Test
    public void numberOfUsersByCountry() throws Exception {
        List<User> users = sampleUsers();

        userEJB.startTransaction();
        persistUsers(users);
        userEJB.commitTransaction();

        assertEquals(2, userEJB.numberOfUsersByCountry("norway"));
    }

    @Test
    public void mostActiveBasedOnPosts() throws Exception {
        List<User> users = sampleUsers();
        Post post = new Post(users.get(0), "sample", new Date());
        Post post1 = new Post(users.get(0), "sample", new Date());
        users.get(0).addPost(post);
        users.get(0).addPost(post1);

        userEJB.startTransaction();
        persistUsers(users);
        PostEJB ejb = new PostEJB(entityManager);
        ejb.save(post);
        ejb.save(post1);
        userEJB.commitTransaction();

        List<User> result = userEJB.mostActiveBasedOnPosts(3);

        assertEquals("most active = ", "thomas", result.get(0).getName());

    }

    @Test
    public void mostActiveBasedOnComments() throws Exception {
        List<User> users = sampleUsers();
        Post post = new Post(users.get(0), "samples", new Date());
        Post post1 = new Post(users.get(0), "samples", new Date());
        users.get(0).addPost(post);
        users.get(0).addPost(post1);

        userEJB.startTransaction();
        persistUsers(users);
        PostEJB ejb = new PostEJB(entityManager);
        ejb.save(post);
        ejb.save(post1);
        userEJB.commitTransaction();

        List<User> result = userEJB.mostActiveBasedOnComments(2);

        assertEquals("most active = ", "thomas", result.get(0).getName());
    }

    @Test
    public void findById() throws Exception {
        User user = sampleUsers().get(0);

        userEJB.startTransaction();
        persistUsers(user);
        userEJB.commitTransaction();

        assertTrue(user.getId() != null); // make sure user is persisted

        User persistedUser = userEJB.findById(user.getId());

        assertTrue(persistedUser.getName().equals("thomas"));
    }

    @Test
    public void save() throws Exception {
        User user1 = sampleUsers().get(0);
        User user2 = sampleUsers().get(1);

        userEJB.startTransaction();
        persistUsers(user1, user2);
        userEJB.commitTransaction();

        assertTrue(user1.getId() != null);
        assertTrue(user2.getId() != null);
    }

    @Test
    public void delete() throws Exception {
        User user = sampleUsers().get(0);

        userEJB.startTransaction();
        persistUsers(user);
        userEJB.commitTransaction();

        userEJB.startTransaction();
        userEJB.delete(user);
        userEJB.commitTransaction();

        assertTrue(userEJB.findById(user.getId()) == null);

    }

    /**
     * Saves the users to the DB
     *
     * @param users
     */
    private void persistUsers(User... users) {
        List<User> userList = new ArrayList<>();
        for (User user : users) {
            userEJB.save(user);
        }
    }

    private void persistUsers(List<User> users) {
        users.forEach(user -> {
            userEJB.save(user);
        });
    }

    /**
     * Some sample users(2)
     *
     * @return a list of them
     */
    private List<User> sampleUsers() {
        List<User> users = new ArrayList<>();

        User user1 = new User("thomas", "dolplads",
                new Address("lokveien 22", "1345", "skui", "norway"), "dd@gmail.com");

        User user2 = new User("ping", "pong",
                new Address("pongVeien", "1234", "oslo", "sweden"), "pong@gmail.com");

        User user3 = new User("per", "hans",
                new Address("lokveien 22", "1345", "skui", "norway"), "perern@gmail.com");


        users.add(user1);
        users.add(user2);
        users.add(user3);

        return users;
    }
}