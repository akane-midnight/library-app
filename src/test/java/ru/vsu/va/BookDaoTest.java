package ru.vsu.va;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class BookDaoTest extends DAOTest {
    private final BookDao bookDao = dbi.onDemand(BookDao.class);
    private final PublisherDao publisherDao = dbi.onDemand(PublisherDao.class);
    private final AuthorDao authorDao = dbi.onDemand(AuthorDao.class);
    private final GenreDao genreDao = dbi.onDemand(GenreDao.class);

    @Test
    public void empty() {
        assertTrue(bookDao.listBooks().isEmpty());
    }

    @Test
    public void addAndList() {
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

        final Author author = new Author();
        author.setAuthorId("authorId");
        author.setAuthorLastname("Kipling");
        author.setAuthorFirstname("R");

        final Genre genre = new Genre();
        genre.setGenreId("genreId");
        genre.setGenreName("Rasskaz");

        genreDao.addGenre(genre);
        authorDao.addAuthor(author);
        publisherDao.addPublisher(publisher);
        bookDao.addBook(book);
        bookDao.addAuthorToBook("bookId", "authorId");
        bookDao.addGenreToBook("bookId", "genreId");

        final List<Book> books = bookDao.listBooks();

        assertNotNull(books);
        assertEquals(1, books.size());
        final Book result = books.get(0);
        assertEquals("bookId", result.getBookId());
        assertEquals("Maugli", result.getTitle());
        assertEquals("2003", result.getBookYear());
        assertEquals("654", result.getQuantityPage());
        assertEquals("pubId", result.getPublisherId());
    }

    @Test
    public void listByWord() {
        final Book book1 = new Book();
        book1.setBookId("bookId1");
        book1.setTitle("Alisa v strane chudes");
        book1.setBookYear("2003");
        book1.setQuantityPage("63");
        book1.setPublisherId("pubId");
        final Book book2 = new Book();
        book2.setBookId("bookId2");
        book2.setTitle("Opyat Alisa v zazerkalie");
        book2.setBookYear("2006");
        book2.setQuantityPage("65");
        book2.setPublisherId("pubId");
        final Book book3 = new Book();
        book3.setBookId("bookId3");
        book3.setTitle("Vii");
        book3.setBookYear("2001");
        book3.setQuantityPage("163");
        book3.setPublisherId("pubId");

        final Publisher publisher = new Publisher();
        publisher.setPublisherId("pubId");
        publisher.setPublisherName("Eksmo");
        publisher.setPublisherCity("Moskva");

        publisherDao.addPublisher(publisher);
        bookDao.addBook(book1);
        bookDao.addBook(book2);
        bookDao.addBook(book3);

        final List<Book> books = bookDao.listBooksByWord("%Alisa%");

        assertNotNull(books);
        assertEquals(2, books.size());
        final Book result1 = books.get(0);
        assertEquals("bookId1", result1.getBookId());
        assertEquals("Alisa v strane chudes", result1.getTitle());
        assertEquals("2003", result1.getBookYear());
        assertEquals("63", result1.getQuantityPage());
        assertEquals("pubId", result1.getPublisherId());
        final Book result2 = books.get(1);
        assertEquals("bookId2", result2.getBookId());
        assertEquals("Opyat Alisa v zazerkalie", result2.getTitle());
        assertEquals("2006", result2.getBookYear());
        assertEquals("65", result2.getQuantityPage());
        assertEquals("pubId", result2.getPublisherId());
    }

    @Test
    public void listBooksGenre() {
        final Book book1 = new Book();
        book1.setBookId("bookId1");
        book1.setTitle("Voina i mir");
        book1.setBookYear("1998");
        book1.setQuantityPage("1603");
        book1.setPublisherId("pubId");
        final Book book2 = new Book();
        book2.setBookId("bookId2");
        book2.setTitle("Opyat Alisa v zazerkalie");
        book2.setBookYear("2006");
        book2.setQuantityPage("65");
        book2.setPublisherId("pubId");
        final Book book3 = new Book();
        book3.setBookId("bookId3");
        book3.setTitle("Evgenii Onegin");
        book3.setBookYear("1983");
        book3.setQuantityPage("213");
        book3.setPublisherId("pubId");

        final Publisher publisher = new Publisher();
        publisher.setPublisherId("pubId");
        publisher.setPublisherName("Eksmo");
        publisher.setPublisherCity("Moskva");

        final Genre genre1 = new Genre();
        genre1.setGenreId("genreId1");
        genre1.setGenreName("Rasskaz");
        final Genre genre2 = new Genre();
        genre2.setGenreId("genreId2");
        genre2.setGenreName("Roman");


        genreDao.addGenre(genre1);
        genreDao.addGenre(genre2);
        publisherDao.addPublisher(publisher);
        bookDao.addBook(book1);
        bookDao.addBook(book2);
        bookDao.addBook(book3);
        bookDao.addGenreToBook("bookId1", "genreId2");
        bookDao.addGenreToBook("bookId2", "genreId1");
        bookDao.addGenreToBook("bookId3", "genreId2");

        final List<Book> books = bookDao.listBooksByGenre("Roman");

        assertNotNull(books);
        assertEquals(2, books.size());
        final Book result1 = books.get(0);
        assertEquals("bookId1", result1.getBookId());
        assertEquals("Voina i mir", result1.getTitle());
        assertEquals("1998", result1.getBookYear());
        assertEquals("1603", result1.getQuantityPage());
        assertEquals("pubId", result1.getPublisherId());
        final Book result2 = books.get(1);
        assertEquals("bookId3", result2.getBookId());
        assertEquals("Evgenii Onegin", result2.getTitle());
        assertEquals("1983", result2.getBookYear());
        assertEquals("213", result2.getQuantityPage());
        assertEquals("pubId", result2.getPublisherId());
    }

    @Test
    public void listBooksAuthor() {
        final Book book1 = new Book();
        book1.setBookId("bookId1");
        book1.setTitle("Alisa v strane chudes");
        book1.setBookYear("2003");
        book1.setQuantityPage("63");
        book1.setPublisherId("pubId");
        final Book book2 = new Book();
        book2.setBookId("bookId2");
        book2.setTitle("Opyat Alisa v zazerkalie");
        book2.setBookYear("2006");
        book2.setQuantityPage("65");
        book2.setPublisherId("pubId");
        final Book book3 = new Book();
        book3.setBookId("bookId3");
        book3.setTitle("Evgenii Onegin");
        book3.setBookYear("1983");
        book3.setQuantityPage("213");
        book3.setPublisherId("pubId");

        final Publisher publisher = new Publisher();
        publisher.setPublisherId("pubId");
        publisher.setPublisherName("Eksmo");
        publisher.setPublisherCity("Moskva");

        final Author author1 = new Author();
        author1.setAuthorId("authorId1");
        author1.setAuthorLastname("Kerroll");
        author1.setAuthorFirstname("L");
        final Author author2 = new Author();
        author2.setAuthorId("authorId2");
        author2.setAuthorLastname("Pushkin");
        author2.setAuthorFirstname("Aleksandr");


        authorDao.addAuthor(author1);
        authorDao.addAuthor(author2);
        publisherDao.addPublisher(publisher);
        bookDao.addBook(book1);
        bookDao.addBook(book2);
        bookDao.addBook(book3);
        bookDao.addAuthorToBook("bookId1", "authorId1");
        bookDao.addAuthorToBook("bookId2", "authorId1");
        bookDao.addAuthorToBook("bookId3", "authorId2");

        final List<Book> books = bookDao.listBooksByAuthor("Kerroll");

        assertNotNull(books);
        assertEquals(2, books.size());
        final Book result1 = books.get(0);
        assertEquals("bookId1", result1.getBookId());
        assertEquals("Alisa v strane chudes", result1.getTitle());
        assertEquals("2003", result1.getBookYear());
        assertEquals("63", result1.getQuantityPage());
        assertEquals("pubId", result1.getPublisherId());
        final Book result2 = books.get(1);
        assertEquals("bookId2", result2.getBookId());
        assertEquals("Opyat Alisa v zazerkalie", result2.getTitle());
        assertEquals("2006", result2.getBookYear());
        assertEquals("65", result2.getQuantityPage());
        assertEquals("pubId", result2.getPublisherId());
    }

}
