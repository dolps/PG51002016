package model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by dolplads on 04/09/16.
 * <p>
 * Class represents News posted on the site
 */
@Entity
@NamedQueries({
        @NamedQuery(name = Post.NUMBER_OF_POSTS, query = "SELECT count(post) FROM Post post"),
        @NamedQuery(name = Post.NUMBER_OF_POSTS_BY_COUNTRY, query = "select count(post)from Post post where post.user.address.countryName = :cName"),
})
public class Post {
    public static final String NUMBER_OF_POSTS_BY_COUNTRY = "NUMBER OF POSTS BY COUNTRY";
    public static final String NUMBER_OF_POSTS = "NUMBER OF POSTS TOTAL";
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private User user;
    @OneToMany
    private List<Comment> userComments;
    private String displayText;
    private Date creationDate;
    private int upVotesCount;
    private int downVotesCount;

    public Post(User user, String displayText, Date creationDate) {
        this.user = user;
        this.displayText = displayText;
        this.creationDate = creationDate;
        this.upVotesCount = 0;
        this.downVotesCount = 0;
        this.userComments = new ArrayList<>();
    }

    public Post() {
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

    public String getDisplayText() {
        return displayText;
    }

    public void setDisplayText(String displayText) {
        this.displayText = displayText;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", user=" + user +
                ", displayText='" + displayText + '\'' +
                ", creationDate=" + creationDate +
                ", upVotesCount=" + upVotesCount +
                ", downVotesCount=" + downVotesCount +
                ", userComments=" + userComments +
                '}';
    }

    /**
     * Adds a comment to this post
     *
     * @param comment
     */
    public void addComment(Comment comment) {
        this.userComments.add(comment);
    }
}
