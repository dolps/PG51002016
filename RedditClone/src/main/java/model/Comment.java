package model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dolplads on 04/09/16.
 */
@Entity
@Data
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue
    private Long id;
    @Size(max = 200)
    private String comment;
    @ManyToOne
    @NotNull
    private User user;

    @NotNull(message = "Cannot be null")
    @ManyToOne
    private Post post;

    @OneToMany
    private List<Comment> comments;

    public Comment(User user, Post post, String comment) {
        this.user = user;
        this.post = post;
        this.comment = comment;
        this.comments = new ArrayList<>();
    }

    public void addComment(Comment comment) {
        this.comments.add(comment);
    }

}
