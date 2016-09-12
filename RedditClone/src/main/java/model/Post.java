package model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
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
@Data
@ToString
@NoArgsConstructor
public class Post {
    public static final String NUMBER_OF_POSTS_BY_COUNTRY = "NUMBER OF POSTS BY COUNTRY";
    public static final String NUMBER_OF_POSTS = "NUMBER OF POSTS TOTAL";
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @NotNull
    @ManyToOne
    private User user;
    @OneToMany
    private List<Comment> userComments;
    @Size(max = 200)
    private String displayText;
    @Past
    @Temporal(TemporalType.DATE)
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

    /**
     * Adds a comment to this post
     *
     * @param comment
     */
    public void addComment(Comment comment) {
        this.userComments.add(comment);
    }
}
