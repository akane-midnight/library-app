package ru.vsu.va;

public class Book {
    private String bookId;
    private String title;
    private String bookYear;
    private String quantityPage;
    private String publisherId;

    public String getBookId() {
        return bookId;
    }

    public void setBookId(final String bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public String getBookYear() {
        return bookYear;
    }

    public void setBookYear(final String bookYear) {
        this.bookYear = bookYear;
    }

    public String getQuantityPage() {
        return quantityPage;
    }

    public void setQuantityPage(final String quantityPage) {
        this.quantityPage = quantityPage;
    }

    public String getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(final String publisherId) {
        this.publisherId = publisherId;
    }
}
