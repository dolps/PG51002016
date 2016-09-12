package repository;

import model.Post;

/**
 * Created by dolplads on 07/09/16.
 */
public interface PostRepository extends CRUDrepository<Post> {
    int findNumberOfPosts();

    int findNumberOfPostsByCountry(String countryName);
}
