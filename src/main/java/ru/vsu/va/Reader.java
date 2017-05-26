package ru.vsu.va;

import java.sql.Date;

public class Reader {
    private String readerId;
    private String lastname;
    private String firstname;
    private Date birthday;

    public String getReaderId() {
        return readerId;
    }

    public void setReaderId(final String readerId) {
        this.readerId = readerId;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(final String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(final String firstname) {
        this.firstname = firstname;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(final Date birthday) {
        this.birthday = birthday;
    }
}
