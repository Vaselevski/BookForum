package cn.it.web.bookforum.comment;
import java.sql.Timestamp;

public class Comment {
    private int commentId;
    private int userId;
    private String username;
    private Integer parentCommentId; // 使用 Integer 以支持 NULL 值
    private int bookId;
    private Timestamp commentCreateAt;
    private int commentLikesCount;
    private String comment;


    // 默认构造函数
    public Comment() {
    }



    // Getter 和 Setter 方法
    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int id) {
        this.commentId = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getParentCommentId() {
        return parentCommentId;
    }

    public void setParentCommentId(Integer parentCommentId) {
        this.parentCommentId = parentCommentId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public Timestamp getCommentCreateAt() {
        return commentCreateAt;
    }

    public void setCommentCreateAt(Timestamp commentCreateAt) {
        this.commentCreateAt = commentCreateAt;
    }

    public int getCommentLikesCount() {
        return commentLikesCount;
    }

    public void setCommentLikesCount(int commentLikesCount) {
        this.commentLikesCount = commentLikesCount;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + commentId +
                ", userId=" + userId +
                ", username='" + username + '\'' +
                ", parentCommentId=" + parentCommentId +
                ", bookId=" + bookId +
                ", commentCreateAt=" + commentCreateAt +
                ", commentLikesCount=" + commentLikesCount +
                ", comment='" + comment + '\'' +
                '}';
    }
}

