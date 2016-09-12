package service;

import model.Comment;
import repository.CommentRepository;

import javax.persistence.EntityManager;

/**
 * Created by dolplads on 08/09/16.
 */
public class CommentEJB extends TransactionAble implements CommentRepository {
    private EntityManager entityManager;

    public CommentEJB(EntityManager entityManager) {
        super(entityManager);
        this.entityManager = entityManager;
    }

    @Override
    public Comment findById(Long id) {
        return entityManager.find(Comment.class, id);
    }

    @Override
    public void save(Comment entity) {
        entityManager.persist(entity);
    }

    @Override
    public void delete(Comment entity) {
        entityManager.remove(entityManager.merge(entity));
    }

    @Override
    public void update(Comment entity) {
        entityManager.remove(entity);
    }
}
