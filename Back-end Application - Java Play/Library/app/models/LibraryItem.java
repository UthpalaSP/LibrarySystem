package models;


public abstract class LibraryItem {

    private String id;

    public String uuid;

    private String title;
    private String sector;
    private DateTime publishedDate;
    private DateTime dateBorrowed;
    private Reader borrower;
    private Integer itemType;
    private String isbn;

    private Integer lateHrs;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public Integer getItemType() {
        return itemType;
    }

    public void setItemType(Integer itemType) {
        this.itemType = itemType;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public DateTime getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(DateTime publishedDate) {
        this.publishedDate = publishedDate;
    }

    public DateTime getDateBorrowed() {
        return dateBorrowed;
    }

    public void setDateBorrowed(DateTime dateBorrowed) {
        this.dateBorrowed = dateBorrowed;
    }

    public Reader getBorrower() {
        return borrower;
    }

    public void setBorrower(Reader borrower) {
        this.borrower = borrower;
    }

    public Integer getLateHrs() {
        return lateHrs;
    }

    public void setLateHrs(Integer lateHrs) {
        this.lateHrs = lateHrs;
    }

    public LibraryItem(String id, String title, String sector, DateTime publishedDate, DateTime dateBorrowed, Integer itemType){

        this.id = id;
        this.title = title;
        this.sector = sector;
        this.publishedDate = null;
        this.dateBorrowed = null;
        this.itemType = itemType;
        this.lateHrs = null;

        this.uuid = java.util.UUID.randomUUID()+"";
    }

    public LibraryItem(){
        this.uuid = java.util.UUID.randomUUID()+"";
    }
}



