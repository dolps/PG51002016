package model;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;
import java.util.List;

/**
 * Created by dolplads on 04/09/16.
 * <p>
 * Class represents News posted on the site
 */
public class Post {
    @Id
    @GeneratedValue
    private Long id;
    private User user;
    private Date creationDate;
    private int upVotesCount;
    private int downVotesCount;
    private List<Comment> userComments;

    public Post(Long id, User user, Date creationDate, int upVotesCount, int downVotesCount, List<Comment> userComments) {
        this.id = id;
        this.user = user;
        this.creationDate = creationDate;
        this.upVotesCount = upVotesCount;
        this.downVotesCount = downVotesCount;
        this.userComments = userComments;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public int getUpVotesCount() {
        return upVotesCount;
    }

    public void setUpVotesCount(int upVotesCount) {
        this.upVotesCount = upVotesCount;
    }

    public int getDownVotesCount() {
        return downVotesCount;
    }

    public void setDownVotesCount(int downVotesCount) {
        this.downVotesCount = downVotesCount;
    }

    public List<Comment> getUserComments() {
        return userComments;
    }

    public void setUserComments(List<Comment> userComments) {
        this.userComments = userComments;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
