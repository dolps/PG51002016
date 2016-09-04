package model;

import java.util.List;

/**
 * Created by dolplads on 04/09/16.
 */
public class Comment {
    List<Comment> commentsOnComment;
    private int upVotesCount;
    private int downVotesCount;

    public Comment(List<Comment> commentsOnComment, int upVotesCount, int downVotesCount) {
        this.commentsOnComment = commentsOnComment;
        this.upVotesCount = upVotesCount;
        this.downVotesCount = downVotesCount;
    }

    public List<Comment> getCommentsOnComment() {
        return commentsOnComment;
    }

    public void setCommentsOnComment(List<Comment> commentsOnComment) {
        this.commentsOnComment = commentsOnComment;
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
}
