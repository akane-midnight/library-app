package ru.vsu.va;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class GenreDaoTest extends DAOTest {
    private final GenreDao genreDao = dbi.onDemand(GenreDao.class);
    private final BookDao bookDao = dbi.onDemand(BookDao.class);
    private final PublisherDao publisherDao = dbi.onDemand(PublisherDao.class);

    private final AuthorDao authorDao = dbi.onDemand(AuthorDao.class);


    @Test
    public void empty() {
        assertTrue(genreDao.listGenres().isEmpty());
    }

    @Test
    public void addAndList() {
        final Genre genre = new Genre();
        genre.setGenreId("genreId");
        genre.setGenreName("Rasskaz");

        genreDao.addGenre(genre);

        final List<Genre> genres = genreDao.listGenres();

        assertNotNull(genres);
        assertEquals(1, genres.size());
        final Genre result = genres.get(0);
        assertEquals("genreId", result.getGenreId());
        assertEquals("Rasskaz", result.getGenreName());
    }

    /*@Test
    public void listBookByGenre() {
        final Genre genre = new Genre();
        genre.setGenreId("genreId");
        genre.setGenreName("Rasskaz");

        final Book book = new Book();
        book.setBookId("bookId");
        book.setTitle("Maugli");
        book.setBookYear("2003");
        book.setQuantityPage("654");
        book.setPublisherId("pubId");

        final Publisher publisher = new Publisher();
        publisher.setPublisherId("pubId");
        publisher.setPublisherName("Eksmo");
        publisher.setPublisherCity("Moskva");

        genreDao.addGenre(genre);
        publisherDao.addPublisher(publisher);
        bookDao.addBook(book);
        bookDao.addGenreToBook("bookId", "genreId");

        final List<GenreBook> genreBook = genreDao.listBookByGenre();

        assertNotNull(genreBook);
        assertEquals(1, genreBook.size());
        final GenreBook result = genreBook.get(0);
        assertEquals("Rasskaz", result.getGenreName());
        assertEquals("Maugli", result.getBookTitle());

    }*/
}
