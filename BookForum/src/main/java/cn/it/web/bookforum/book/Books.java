package cn.it.web.bookforum.book;



public class Books {
    private int id;
    private String isbn;
    private String name;
    private String author;
    private String introduction;
    private String authorNationality;
    private Double price;
    private int publicationYear;
    private int likesCount;
    private String type;

    public Books(){
    }
    public Books(String isbn,String name, String author, String introduction,String authorNationality, Double price, int publicationYear, int likesCount, String type){
        this.isbn = isbn;
        this.name = name;
        this.author = author;
        this.introduction = introduction;
        this.authorNationality = authorNationality;
        this.price = price;
        this.publicationYear = publicationYear;
        this.likesCount = likesCount;
        this.type = type;
    }

    public Books(int id,String isbn, String name, String author, String introduction,String authorNationality, Double price, int publicationYear, int likesCount, String type) {
        this.id = id;
        this.isbn = isbn;
        this.name = name;
        this.author = author;
        this.introduction = introduction;
        this.authorNationality = authorNationality;
        this.price = price;
        this.publicationYear = publicationYear;
        this.likesCount = likesCount;
        this.type = type;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getAuthorNationality() {
        return authorNationality;
    }

    public void setAuthorNationality(String authorNationality) {
        this.authorNationality = authorNationality;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    public int getLikesCount() {
        return likesCount;
    }

    public void setLikesCount(int likesCount) {
        this.likesCount = likesCount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Books Information:\n" +
                "ID: " + id + "\n" +
                "ISBN: " + isbn + "\n" +
                "Name: " + name + "\n" +
                "Author: " + author + "\n" +
                "Introduction: " + introduction + "\n" +
                "Author Nationality: " + authorNationality + "\n" +
                "Price: $" + price + "\n" +
                "Publication Year: " + publicationYear + "\n" +
                "Likes Count: " + likesCount + "\n" +
                "Type: " + type;
    }

}
