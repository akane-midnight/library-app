package ru.vsu.va;

import java.sql.Date;

public class GiveNoteBook {

    private String giveId;
    private Date giveDate;
    private Date returnDate;
    private Date realReturnDate;
    private String readerLastname;
    private String readerFirstname;

    public String getGiveId() {
        return giveId;
    }

    public void setGiveId(final String giveId) {
        this.giveId = giveId;
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

    public Date getRealReturnDate() {
        return realReturnDate;
    }

    public void setRealReturnDate(final Date realReturnDate) {
        this.realReturnDate = realReturnDate;
    }

    public String getReaderLastname() {
        return readerLastname;
    }

    public void setReaderLastname(final String readerLastname) {
        this.readerLastname = readerLastname;
    }

    public String getReaderFirstname() {
        return readerFirstname;
    }

    public void setReaderFirstname(final String readerFirstname) {
        this.readerFirstname = readerFirstname;
    }
}
