package ru.vsu.va;

import java.sql.Date;

public class GiveNoteReader {
    private String giveId;
    private String title;
    private String authorName;
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
