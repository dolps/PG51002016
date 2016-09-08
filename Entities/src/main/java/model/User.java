package model;

import javax.persistence.*;
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
public class User {
    public static final String GET_ALL = "GET_ALL";
    public static final String GET_NUMBER_OF_USERS = "GET_NUMBER_OF_USERS";
    public static final String GET_ALL_BY_COUNTRY = "GET_ALL_IN_NORWAY";
    public static final String DISTINCT_COUNTRIES = "DISTINT_COUNTRIES";
    public static final String GET_NUMBER_OF_USERS_FROM_COUNTRY = "NUMB_OF_USERS_FROM_COUNTRY";
    public static final String MOST_ACTIVE_POSTER = "MOST_ACTIVE_POSTER";
    public static final String MOST_ACTIVE_COMMENTER = "MOST_ACTIVE_COMMENTER";

    @Id
    @GeneratedValue
    private Long id;
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

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return id.equals(user.id);

    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    public void addPost(Post post1) {
        this.posts.add(post1);
    }
    public void addComment(Post post,Comment comment){
        post.addComment(comment);
    }
}
