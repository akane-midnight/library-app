package ru.vsu.va;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;
import org.skife.jdbi.v2.sqlobject.mixins.Transactional;

import java.util.List;

@RegisterMapper({BookMapper.class, GiveNoteBookMapper.class})
public interface BookDao extends Transactional<BookDao> {
    @SqlUpdate("INSERT INTO books (book_id, title, book_year, quantity_page, publisher_id) " +
            "VALUES (:bookId, :title, :bookYear, :quantityPage, :publisherId)")
    void addBook(@BindBean Book book);

    @SqlQuery("SELECT * FROM books")
    List<Book> listBooks();

    @SqlUpdate("INSERT INTO book_author (book_id, author_id) VALUES (:bookId, :authorId)")
    void addAuthorToBook(@Bind("bookId") String bookId, @Bind("authorId") String authorId);

    @SqlUpdate("INSERT INTO book_genre (book_id, genre_id) VALUES (:bookId, :genreId)")
    void addGenreToBook(@Bind("bookId") String bookId, @Bind("genreId") String genreId);

    @SqlQuery("SELECT * FROM books WHERE title LIKE :word")
    List<Book> listBooksByWord(@Bind("word") String word);

    @SqlQuery("SELECT books.book_id, books.title, books.book_year, books.quantity_page, " +
            "books.publisher_id FROM book_genre " +
            "JOIN genres ON genres.genre_id=book_genre.genre_id " +
            "JOIN books ON books.book_id=book_genre.book_id " +
            "WHERE genre_name = :genre")
    List<Book> listBooksByGenre(@Bind("genre") String genre);

    @SqlQuery("SELECT books.book_id, books.title, books.book_year, books.quantity_page, " +
            "books.publisher_id FROM book_author " +
            "JOIN authors ON authors.author_id=book_author.author_id " +
            "JOIN books ON books.book_id=book_author.book_id " +
            "WHERE author_lastname = :author")
    List<Book> listBooksByAuthor(@Bind("author") String author);

    @SqlQuery("SELECT book_give_list.give_id, book_give_list.give_date, book_give_list.return_date, book_give_list.real_return_date, " +
            "readers.lastname, readers.firstname FROM book_give_list " +
            //"JOIN books ON books.book_id = book_give_list.book_id " +
            "JOIN readers ON readers.reader_id = book_give_list.reader_id " +
            "WHERE book_id = :id_book")
    List<GiveNoteBook> listBookGives(@Bind("id_book") String bookId);
}
