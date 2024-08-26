package cn.it.web.bookforum.entityclass;


public class Book {
    private int bookId;
    private String bookName;
    private String bookAuthor;
    private String bookIntroduction;
    private String bookAuthorIntroduction;
    private double bookPrice;
    private int bookPublicationYear;
    private String bookPublisher;
    private long bookTotalScore;
    private int bookRatePeople;
    private double bookScore;
    private String bookType;

    public Book() {
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public String getBookIntroduction() {
        return bookIntroduction;
    }

    public void setBookIntroduction(String bookIntroduction) {
        this.bookIntroduction = bookIntroduction;
    }

    public String getBookAuthorIntroduction() {
        return bookAuthorIntroduction;
    }

    public void setBookAuthorIntroduction(String bookAuthorIntroduction) {
        this.bookAuthorIntroduction = bookAuthorIntroduction;
    }

    public double getBookPrice() {
        return bookPrice;
    }

    public void setBookPrice(double bookPrice) {
        this.bookPrice = bookPrice;
    }

    public int getBookPublicationYear() {
        return bookPublicationYear;
    }

    public void setBookPublicationYear(int bookPublicationYear) {
        this.bookPublicationYear = bookPublicationYear;
    }

    public String getBookPublisher() {
        return bookPublisher;
    }

    public void setBookPublisher(String bookPublisher) {
        this.bookPublisher = bookPublisher;
    }

    public long getBookTotalScore() {
        return bookTotalScore;
    }

    public void setBookTotalScore(long bookTotalScore) {
        this.bookTotalScore = bookTotalScore;
    }

    public int getBookRatePeople() {
        return bookRatePeople;
    }

    public void setBookRatePeople(int bookRatePeople) {
        this.bookRatePeople = bookRatePeople;
    }

    public double getBookScore() {
        return bookScore;
    }

    public void setBookScore(double bookScore) {
        this.bookScore = bookScore;
    }

    public String getBookType() {
        return bookType;
    }

    public void setBookType(String bookType) {
        this.bookType = bookType;
    }

    @Override
    public String toString() {
        return "Books{" +
                "bookId=" + bookId +
                ", bookName='" + bookName + '\'' +
                ", bookAuthor='" + bookAuthor + '\'' +
                ", bookIntroduction='" + bookIntroduction + '\'' +
                ", bookAuthorIntroduction='" + bookAuthorIntroduction + '\'' +
                ", bookPrice=" + bookPrice +
                ", bookPublicationYear=" + bookPublicationYear +
                ", bookPublisher='" + bookPublisher + '\'' +
                ", bookTotalScore=" + bookTotalScore +
                ", bookRatePeople=" + bookRatePeople +
                ", bookScore=" + bookScore +
                ", bookType='" + bookType + '\'' +
                '}';
    }
}
