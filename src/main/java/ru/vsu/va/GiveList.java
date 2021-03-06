package ru.vsu.va;

import java.sql.Date;

public class GiveList {
    private String giveId;
    private String bookId;
    private String readerId;
    private Date giveDate;
    private Date returnDate;
    //private Date realReturnDate;

    public String getGiveId() {
        return giveId;
    }

    public void setGiveId(final String giveId) {
        this.giveId = giveId;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(final String bookId) {
        this.bookId = bookId;
    }

    public String getReaderId() {
        return readerId;
    }

    public void setReaderId(final String readerId) {
        this.readerId = readerId;
    }

    public Date getGiveDate() {
        return giveDate;
    }

    public void setGiveDate(final Date giveDate) {
        this.giveDate = giveDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(final Date returnDate) {
        this.returnDate = returnDate;
    }

    //public Date getRealReturnDate() { return realReturnDate; }

    //public void setRealReturnDate(final Date realReturnDate) { this.realReturnDate = realReturnDate; }
}
