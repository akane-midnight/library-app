package ru.vsu.va;

public class Publisher {
    private String publisherId;
    private String publishername;
    private String publishercity;

    public String getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(final String publisherId) {
        this.publisherId = publisherId;
    }

    public String getPublisherName() {
        return publishername;
    }

    public void setPublisherName(final String publishername) {
        this.publishername = publishername;
    }

    public String getPublisherCity() {
        return publishercity;
    }

    public void setPublisherCity(final String publishercity) {
        this.publishercity = publishercity;
    }
}
