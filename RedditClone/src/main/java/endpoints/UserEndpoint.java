package endpoints;

import model.User;
import service.UserEJB;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 * Created by dolplads on 08/09/16.
 * <p>
 * This class handles JSF or rest endpoints
 */
@Named
@RequestScoped
public class UserEndpoint {
    private UserEJB userEJB;

    public String addUser(User user) {
        if (userEJB == null)
            throw new NullPointerException(" userEJB is null");
        userEJB.save(user);
        return "customer added";
    }
}
