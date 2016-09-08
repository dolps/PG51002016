package repository;

/**
 * Created by dolplads on 07/09/16.
 */
public interface CRUDrepository<T> {
    T findById(Long id);

    void save(T entity);

    void delete(T entity);

    void update(T entity);
}
