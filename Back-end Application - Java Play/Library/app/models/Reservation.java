package models;

public class Reservation {
    public String isbn;
    public Reader reserver;
    public DateTime reservedDate;
    public DateTime availableDate;

    public Reservation(String isbn, Reader reserver, DateTime reservedDate, DateTime availableDate) {
        this.isbn = isbn;
        this.reserver = reserver;
        this.reservedDate = reservedDate;
        this.availableDate = availableDate;
    }
}
