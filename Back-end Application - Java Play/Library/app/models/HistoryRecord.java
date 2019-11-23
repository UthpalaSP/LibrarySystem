package models;

public class HistoryRecord {
    public String isbn;
    public Reader borrower;
    public DateTime borrowedDate;
    public DateTime returnDate;
    public int hrsOccupied;

    public HistoryRecord(String isbn, Reader borrower, DateTime borrowedDate) {
        this.isbn = isbn;
        this.borrower = borrower;
        this.borrowedDate = borrowedDate;
        this.returnDate = null;
    }

    public HistoryRecord(String isbn, Reader borrower, DateTime borrowedDate, DateTime returnDate) {
        this.isbn = isbn;
        this.borrower = borrower;
        this.borrowedDate = borrowedDate;
        this.returnDate = returnDate;
        this.hrsOccupied = returnDate.substract(borrowedDate);
    }

    public HistoryRecord(){}

}
