package service;

import lombok.extern.java.Log;
import model.Address;
import model.Comment;
import model.Post;
import model.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.Assert.*;

/**
 * Created by dolplads on 08/09/16.
 */
@Log
public class CommentEJBTest {
    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

    private PostEJB postEJB;
    private UserEJB userEJB;
    private CommentEJB commentEJB;

    @Before
    public void setUp() throws Exception {
        this.entityManagerFactory = Persistence.createEntityManagerFactory("H2DB");
        this.entityManager = entityManagerFactory.createEntityManager();
        this.postEJB = new PostEJB(entityManager);
        this.userEJB = new UserEJB(entityManager);
        this.commentEJB = new CommentEJB(entityManager);
    }

    @After
    public void tearDown() throws Exception {
        entityManager.close();
        entityManagerFactory.close();
    }

    @Test
    public void findById() throws Exception {

    }

    @Test
    public void save() throws Exception {
        Address address = new Address("street", "1234", "oslo", "norway");
        User testUser = new User("thomas", "dolplad", address, "dolplads@gmail.com");
        User testUser2 = new User("thomas", "dolplad", address, "dolplads@gmail.com");
        Post post = new Post(testUser, "displayText", new Date());
        String text = "this is a comment";
        Comment comment = new Comment(text);
        post.addComment(comment);
        testUser.addPost(post);

        assertTrue(persistInATransaction(testUser2, testUser, post, comment));

        System.out.println(post.getId());
        Post postTest = postEJB.findById(post.getId());
        assertTrue(postTest != null);
        System.out.println(postTest.getUserComments().get(0).getComment());
        assertEquals(postTest.getDisplayText(), "displayText");
        System.out.println("user who wrote the post " + postTest.getUser().getId());

        User user2 = userEJB.findById(postTest.getUser().getId());
        System.out.println(user2.getPosts().get(0).getDisplayText());
        user2.getPosts().get(0).setDisplayText("this is other text");

        user2 = null;
        user2 = userEJB.findById(postTest.getUser().getId());
        entityManager.clear();
        System.out.println(user2.getPosts().get(0).getDisplayText());

    }

    @Test
    public void delete() throws Exception {

    }

    @Test
    public void update() throws Exception {

    }

    private boolean persistInATransaction(Object... obj) {
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();

        try {
            for (Object o : obj) {
                entityManager.persist(o);
            }
            tx.commit();
        } catch (Exception e) {
            log.log(Level.INFO, "FAILED TRANSACTION " + e.toString());

            tx.rollback();
            return false;
        }

        return true;
    }

}