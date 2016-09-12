package repository;

import model.User;

import java.util.List;
import java.util.Set;

/**
 * Created by dolplads on 07/09/16.
 */
public interface UserRepository extends CRUDrepository<User> {

    Set<String> getCountriesUsersRepresent();

    int numberOfUsers();

    int numberOfUsersByCountry(String countryName);

    /**
     * returns the users that has written the most posts
     *
     * @param limit
     * @return
     */
    List<User> mostActiveBasedOnPosts(int limit);

    /**
     * returns the users that has written the most comments
     *
     * @param limit
     * @return
     */
    List<User> mostActiveBasedOnComments(int limit);


}
