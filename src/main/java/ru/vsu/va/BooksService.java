package ru.vsu.va;

import org.skife.jdbi.v2.DBI;

import javax.inject.Inject;
import java.util.List;

public class BooksService {
    private final DBI dbi;
    private final IdGenerator idGenerator;

    @Inject
    public BooksService(final DBI dbi, final IdGenerator idGenerator) {
        this.dbi = dbi;
        this.idGenerator = idGenerator;
    }

    public void addBook(final String title,
                        final String bookYear,
                        final int quantityPage,
                        final String publisherId,
                        final List<String> authorIdList,
                        final List<String> genreIdList) {
        dbi.inTransaction((handle, status) -> {
            final BookDao bookDao = handle.attach(BookDao.class);
            final AuthorDao authorDao = handle.attach(AuthorDao.class);
            final GenreDao genreDao = handle.attach(GenreDao.class);

            final String bookId = idGenerator.generateId();

            final Book book = new Book();
            book.setBookId(bookId);
            book.setTitle(title);
            book.setBookYear(bookYear);
            book.setQuantityPage(quantityPage);
            book.setPublisherId(publisherId);
            bookDao.addBook(book);

            for (final String authorId : authorIdList) {
                if (authorDao.getAuthor(authorId) == null) {
                    throw new IllegalArgumentException("Author with provided id is not presented in DB");
                }
                bookDao.addAuthorToBook(bookId, authorId);
            }

            for (final String genreId : genreIdList) {
                if (genreDao.getGenre(genreId) == null) {
                    throw new IllegalArgumentException("Genre with provided id is not presented in DB");
                }
                bookDao.addGenreToBook(bookId, genreId);
            }

            return null;
        });
    }

    public List<Book> bookByGenre(final String genreName) {
        final BookDao bookDao = dbi.onDemand(BookDao.class);
        return bookDao.listBooksByGenre(genreName);
    }

    public List<Book> bookByAuthor(final String authorLastname) {
        final BookDao bookDao = dbi.onDemand(BookDao.class);
        return bookDao.listBooksByAuthor(authorLastname);
    }

    public List<Book> bookByWord(final String wordOfTitle) {
        final BookDao bookDao = dbi.onDemand(BookDao.class);
        return bookDao.listBooksByWord(wordOfTitle);
    }

    public List<GiveNoteBook> bookGives(final String idBook) {
        final BookDao bookDao = dbi.onDemand(BookDao.class);
        return bookDao.listBookGives(idBook);
    }


}
