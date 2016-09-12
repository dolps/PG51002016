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
public class PostEJBTest {
    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;
    private PostEJB postEJB;
    private UserEJB userEJB;

    @Before
    public void setUp() throws Exception {
        this.entityManagerFactory = Persistence.createEntityManagerFactory("H2DB");
        this.entityManager = entityManagerFactory.createEntityManager();
        this.postEJB = new PostEJB(entityManager);
        this.userEJB = new UserEJB(entityManager);
    }

    @After
    public void tearDown() throws Exception {
        entityManager.close();
        entityManagerFactory.close();
    }

    @Test
    public void findNumberOfPosts() throws Exception {
        User testUser = sampleUsers().get(0);
        Post post1 = new Post(testUser, "this is testpost", new Date());
        Post post2 = new Post(testUser, "this is testpost2", new Date());
        testUser.addPost(post1);
        testUser.addPost(post2);

        userEJB.startTransaction();
        userEJB.save(testUser);
        postEJB.save(post1);
        postEJB.save(post2);
        userEJB.commitTransaction();

        assertEquals(2, postEJB.findNumberOfPosts());
    }

    @Test
    public void findNumberOfPostsByCountry() throws Exception {
        User testUser = sampleUsers().get(0);
        Post post1 = new Post(testUser, "this is testpost", new Date());
        Post post2 = new Post(testUser, "this is testpost2", new Date());
        testUser.addPost(post1);
        testUser.addPost(post2);

        userEJB.startTransaction();
        userEJB.save(testUser);
        postEJB.save(post1);
        postEJB.save(post2);
        userEJB.commitTransaction();

        assertEquals(2, postEJB.findNumberOfPostsByCountry("norway"));

    }

    @Test
    public void findById() throws Exception {

    }

    @Test
    public void save() throws Exception {
        List<User> testUsers = sampleUsers();
        // save all users
        userEJB.startTransaction();
        persistUsers(testUsers);
        userEJB.commitTransaction();

        User testSubject = testUsers.get(0);
        String postText = "this is a testpost";
        Post post1 = new Post(testSubject, postText, new Date());

        // save all posts
        postEJB.startTransaction();
        postEJB.save(post1);
        postEJB.commitTransaction();

        // update the users with the posts
        testSubject.addPost(post1);
        userEJB.startTransaction();
        userEJB.update(testSubject);
        userEJB.commitTransaction();

        User persistedUser = userEJB.findById(1L);
        assertTrue(persistedUser.getPosts().get(0).getDisplayText().equals(postText));
    }

    @Test
    public void delete() throws Exception {

    }

    @Test
    public void update() throws Exception {

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

        User user1 = new User("thomass", "dolplads",
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