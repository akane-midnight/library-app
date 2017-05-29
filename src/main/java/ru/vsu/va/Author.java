package ru.vsu.va;

public class Author {
    private String authorId;
    private String authorLastname;
    private String authorFirstname;

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(final String authorId) {
        this.authorId = authorId;
    }

    public String getAuthorLastname() {
        return authorLastname;
    }

    public void setAuthorLastname(final String authorLastname) {
        this.authorLastname = authorLastname;
    }

    public String getAuthorFirstname() {
        return authorFirstname;
    }

    public void setAuthorFirstname(final String authorFirstname) {
        this.authorFirstname = authorFirstname;
    }
}
