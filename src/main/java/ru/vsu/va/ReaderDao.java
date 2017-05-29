package ru.vsu.va;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;
import org.skife.jdbi.v2.sqlobject.mixins.Transactional;

import java.util.List;

@RegisterMapper({ReaderMapper.class, GiveNoteReaderMapper.class})
public interface ReaderDao extends Transactional<ReaderDao> {
    @SqlUpdate("INSERT INTO readers (reader_id, lastname, firstname, birthday) " +
            "VALUES (:readerId, :lastname, :firstname, :birthday)")
    void addReader(@BindBean Reader reader);

    @SqlQuery("SELECT * FROM readers")
    List<Reader> listReaders();

    @SqlQuery("SELECT * FROM readers WHERE lastname = :name")
    List<Reader> listReadersByLastame(@Bind("name") String name);

    @SqlQuery("SELECT * FROM readers WHERE lastname LIKE :letter")
    List<Reader> listReadersByLetter(@Bind("letter") String letter);

    @SqlQuery("SELECT book_give_list.give_id, books.title, authors.author_lastname, book_give_list.give_date, book_give_list.return_date, book_give_list.real_return_date FROM book_give_list " +
            "JOIN books ON books.book_id = book_give_list.book_id " +
            "JOIN book_author ON book_author.book_id = book_give_list.book_id " +
            "JOIN authors ON authors.author_id = book_author.author_id " +
            "WHERE author_first = true AND reader_id = :id_reader")
    List<GiveNoteReader> listReaderBooks(@Bind("id_reader") String readerId);

    @SqlQuery("SELECT * FROM readers WHERE lastname = :l_name AND firstname = :f_name")
    Reader getReader(@Bind("l_name") String lName, @Bind("f_name") String fName);
}
