package ru.vsu.va;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;
import org.skife.jdbi.v2.sqlobject.mixins.Transactional;

@RegisterMapper(BookExemplarMapper.class)
public interface BookExemplarDao extends Transactional<BookExemplarDao> {
    @SqlUpdate("INSERT INTO book_exemplars (book_exemplar_id, description, book_id) " +
            "VALUES (:bookExemplarId, :description, :bookId)")
    void addBookExemplar(@BindBean BookExemplar book_exemplar);

    @SqlQuery("SELECT book_exemplars.book_exemplar_id, books.title, book_exemplars.book_description FROM book_exemplars " +
            "JOIN books ON books.book_id=book_exemplars.book_id " +
            "WHERE title = :book_title")
    List<Book> listBookExemplars(@Bind("book_title") String bookTitle);
}
