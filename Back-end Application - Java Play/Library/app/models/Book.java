package models;

public class Book extends LibraryItem{


    private Integer noOfPages;
    private String authors;
    private String publisher;

    public Integer getNoOfPages() {
        return noOfPages;
    }

    public void setNoOfPages(Integer noOfPages) {
        this.noOfPages = noOfPages;
    }

    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Book(String id, String title, String sector, DateTime publishedDate, DateTime dateBorrowed, Integer itemType) {
        super(id, title, sector, publishedDate, dateBorrowed, itemType);
    }

    public Book(){
    }
}
