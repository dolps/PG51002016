package model;

import lombok.Data;
import lombok.EqualsAndHashCode;
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
@NamedQueries({
        @NamedQuery(name = User.GET_ALL, query = "select user from User user"),
        @NamedQuery(name = User.GET_ALL_BY_COUNTRY, query = "select count (user) from User user where user.address.countryName = :cName"),
        @NamedQuery(name = User.DISTINCT_COUNTRIES, query = "select DISTINCT user.address.countryName from User user"),
        @NamedQuery(name = User.GET_NUMBER_OF_USERS, query = "select count (user) from User user"),
        @NamedQuery(name = User.GET_NUMBER_OF_USERS_FROM_COUNTRY, query =
                "select count (user) from User user where user.address.countryName = :cName"),
        @NamedQuery(name = User.MOST_ACTIVE_POSTER, query =
                "select user from User user order by user.posts.size desc"),
        @NamedQuery(name = User.MOST_ACTIVE_COMMENTER, query =
                "select user from User user order by user.posts.size desc")
})
@Data
@EqualsAndHashCode
@NoArgsConstructor
public class User {
    public static final String GET_ALL = "GET_ALL";
    public static final String GET_NUMBER_OF_USERS = "GET_NUMBER_OF_USERS";
    public static final String GET_ALL_BY_COUNTRY = "GET_ALL_IN_NORWAY";
    public static final String DISTINCT_COUNTRIES = "DISTINT_COUNTRIES";
    public static final String GET_NUMBER_OF_USERS_FROM_COUNTRY = "NUMB_OF_USERS_FROM_COUNTRY";
    public static final String MOST_ACTIVE_POSTER = "MOST_ACTIVE_POSTER";
    public static final String MOST_ACTIVE_COMMENTER = "MOST_ACTIVE_COMMENTER";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @NotNull
    private Long id;
    @Size(max = 2000)
    private String name;

    private String surName;
    @Embedded
    private Address address;
    private String email;
    @OneToMany
    private List<Post> posts;

    public User(String name, String surName, Address address, String email) {
        this.name = name;
        this.surName = surName;
        this.address = address;
        this.email = email;
        this.posts = new ArrayList<>();
    }


    public void addPost(Post post) {
        this.posts.add(post);
    }

    public void addCommentToPost(Post post) {

    }

    public void addCommentToComment(Comment comment) {
    }

}
