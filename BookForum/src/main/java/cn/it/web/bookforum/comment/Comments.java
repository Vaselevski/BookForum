package cn.it.web.bookforum.comment;

import java.sql.Timestamp;

public class Comments {
    private int id;
    private int userId;
    private int bookId;
    private Timestamp createAt;
    private int dislikesCount;
    private int likesCount;
    private String comment;

    // Getter and Setter methods
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public Timestamp getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Timestamp createAt) {
        this.createAt = createAt;
    }

    public int getDislikesCount() {
        return dislikesCount;
    }

    public void setDislikesCount(int dislikesCount) {
        this.dislikesCount = dislikesCount;
    }

    public int getLikesCount() {
        return likesCount;
    }

    public void setLikesCount(int likesCount) {
        this.likesCount = likesCount;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "Comments Information:\n" +
                "ID: " + id + "\n" +
                "User ID: " + userId + "\n" +
                "Book ID: " + bookId + "\n" +
                "Created At: " + createAt + "\n" +
                "Dislikes Count: " + dislikesCount + "\n" +
                "Likes Count: " + likesCount + "\n" +
                "Comment: " + comment;
    }

    public Comments(){

    }

    public Comments(int userId,int bookId,String comment) {
        this.userId = userId;
        this.bookId = bookId;
        this.comment = comment;
    }
    public Comments(int id,int userId,int bookId,Timestamp createAt,int dislikesCount,int likesCount,String comment) {
        this.id = id;
        this.userId = userId;
        this.bookId = bookId;
        this.createAt = createAt;
        this.dislikesCount = dislikesCount;
        this.likesCount = likesCount;
        this.comment = comment;
    }

}
