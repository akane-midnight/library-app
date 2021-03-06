package ru.vsu.va;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;
import org.skife.jdbi.v2.sqlobject.mixins.Transactional;

import java.util.List;

@RegisterMapper(AuthorMapper.class)
public interface AuthorDao extends Transactional<AuthorDao> {
    @SqlUpdate("INSERT INTO authors (author_id, author_lastname, author_firstname) " +
            "VALUES (:authorId, :authorLastname, :authorFirstname)")
    void addAuthor(@BindBean Author author);

    @SqlQuery("SELECT * FROM authors")
    List<Author> listAuthors();


    @SqlQuery("SELECT * FROM authors WHERE author_id = :authorId")
    Author getAuthor(@Bind("authorId") String authorId);

    @SqlQuery("SELECT * FROM authors WHERE author_lastname = :author_lastname")
    Author getAuthorByLastname(@Bind("author_lastname") String authorLastname);

    @SqlQuery("SELECT * FROM authors WHERE author_lastname = :name")
    List<Author> listAuthorByLastname(@Bind("name") String name);

    @SqlQuery("SELECT * FROM authors WHERE author_lastname LIKE :letter")
    List<Author> listAuthorsByLetter(@Bind("letter") String letter);

}
