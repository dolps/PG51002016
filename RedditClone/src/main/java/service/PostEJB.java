package service;

import model.Post;
import repository.PostRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

/**
 * Created by dolplads on 08/09/16.
 */
public class PostEJB extends TransactionAble implements PostRepository {
    private EntityManager entityManager;

    public PostEJB(EntityManager entityManager) {
        super(entityManager);
        this.entityManager = entityManager;
    }

    @Override
    public int findNumberOfPosts() {
        Long result = (Long) entityManager.createNamedQuery(Post.NUMBER_OF_POSTS).getSingleResult();

        return Math.toIntExact(result);
    }

    @Override
    public int findNumberOfPostsByCountry(String countryName) {
        Long result = (Long) entityManager
                .createNamedQuery(Post.NUMBER_OF_POSTS_BY_COUNTRY)
                .setParameter("cName", countryName)
                .getSingleResult();

        return Math.toIntExact(result);
    }

    @Override
    public Post findById(Long id) {
        return entityManager.find(Post.class, id);
    }

    @Override
    public void save(Post entity) {
        entityManager.persist(entity);
    }

    @Override
    public void delete(Post entity) {

    }

    @Override
    public void update(Post entity) {

    }

    public List<Post> findAll() {
        return entityManager.createQuery("select p from Post p").getResultList();
    }
}
