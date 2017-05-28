package ru.vsu.va;

import java.sql.Date;

public class GiveNoteDate {
    private String giveId;
    private String title;
    private String authorName;
    private String readerLastname;
    private String readerFirstname;
    private Date giveDate;
    private Date returnDate;
    private Date realReturnDate;

    public String getGiveId() {
        return giveId;
    }

    public void setGiveId(final String giveId) {
        this.giveId = giveId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(final String authorName) {
        this.authorName = authorName;
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
}

