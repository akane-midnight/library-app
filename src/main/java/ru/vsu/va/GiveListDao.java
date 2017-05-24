package ru.vsu.va;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;
import org.skife.jdbi.v2.sqlobject.mixins.Transactional;

import java.util.Date;
import java.util.List;

@RegisterMapper({GiveListMapper.class, GiveNoteMapper.class})
public interface GiveListDao extends Transactional<GiveListDao> {
    @SqlUpdate("INSERT INTO book_give_list (give_id, book_id, person_id, give_date, return_date, real_return_date) " +
            "VALUES (:giveId, :bookId, :personId, :giveDate, :returnDate, :realReturnDate)")
    void addInGiveList(@BindBean GiveList giveList);

    @SqlQuery("SELECT book_give_list.give_id, books.title, authors.author_lastname, persons.lastname, " +
            "persons.firstname, book_give_list.give_date, book_give_list.return_date, " +
            "book_give_list.real_return_date FROM book_give_list " +
            "JOIN books ON books.book_id = book_give_list.book_id " +
            "JOIN book_author ON book_author.book_id = book_give_list.book_id " +
            "JOIN authors ON authors.author_id = book_author.author_id " +
            "JOIN persons ON persons.person_id = book_give_list.person_id " +
            "WHERE author_first = true")
    List<GiveNote> listGiveList();

    @SqlQuery("SELECT book_give_list.give_id, books.title, persons.lastname, persons.firstname, " +
            "book_give_list.give_date, book_give_list.return_date, book_give_list.real_return_date " +
            "FROM book_give_list " +
            "JOIN books ON books.book_id = book_give_list.book_id " +
            "JOIN persons ON persons.person_id = book_give_list.person_id " +
            "WHERE return_date = :r_date")
    List<GiveNote> listGiveListByReturnDate(@Bind("date") Date r_date);
}
