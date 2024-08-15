package cn.it.web.bookforum.bookscore;

public class BookScore {
    private int bookId;
    private int userId;
    private int score;
    public BookScore() {}
    public void setBookId(int bookId) {
        this.bookId = bookId;
    }
    public int getBookId() {
        return bookId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public int getUserId() {
        return userId;
    }
    public void setScore(int score) {
        this.score = score;
    }
    public int getScore() {
        return score;
    }
}
