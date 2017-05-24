package ru.vsu.va;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class AuthorDaoTest extends DAOTest {
    private final AuthorDao authorDao = dbi.onDemand(AuthorDao.class);
    private final BookDao bookDao = dbi.onDemand(BookDao.class);
    private final GenreDao genreDao = dbi.onDemand(GenreDao.class);

    @Test
    public void empty() {
        assertTrue(authorDao.listAuthor().isEmpty());
    }

    @Test
    public void addAndList() {
        final Author author = new Author();
        author.setAuthorId("authorId");
        author.setAuthorLastname("Tolstoi");
        author.setAuthorFirstname("Lev");

        authorDao.addAuthor(author);

        final List<Author> authors = authorDao.listAuthor();

        assertNotNull(authors);
        assertEquals(1, authors.size());
        final Author result = authors.get(0);
        assertEquals("authorId", result.getAuthorId());
        assertEquals("Tolstoi", result.getAuthorLastname());
        assertEquals("Lev", result.getAuthorFirstname());
    }

    @Test
    public void listByLastname() {
        final Author author1 = new Author();
        author1.setAuthorId("authorId1");
        author1.setAuthorLastname("Tolstoi");
        author1.setAuthorFirstname("Lev");
        final Author author2 = new Author();
        author2.setAuthorId("authorId2");
        author2.setAuthorLastname("Gogol");
        author2.setAuthorFirstname("Nikolai");

        authorDao.addAuthor(author1);
        authorDao.addAuthor(author2);

        final List<Author> authors = authorDao.listAuthorByLastname("Gogol");

        assertNotNull(authors);
        assertEquals(1, authors.size());
        final Author result = authors.get(0);
        assertEquals("authorId2", result.getAuthorId());
        assertEquals("Gogol", result.getAuthorLastname());
        assertEquals("Nikolai", result.getAuthorFirstname());
    }

    @Test
    public void listByLetter() {
        final Author author1 = new Author();
        author1.setAuthorId("authorId1");
        author1.setAuthorLastname("Tolstoi");
        author1.setAuthorFirstname("Lev");
        final Author author2 = new Author();
        author2.setAuthorId("authorId2");
        author2.setAuthorLastname("Gogol");
        author2.setAuthorFirstname("Nikolai");
        final Author author3 = new Author();
        author3.setAuthorId("authorId3");
        author3.setAuthorLastname("Goncharov");
        author3.setAuthorFirstname("Ivan");

        authorDao.addAuthor(author1);
        authorDao.addAuthor(author2);
        authorDao.addAuthor(author3);

        final List<Author> authors = authorDao.listAuthorByLetter("G%");

        assertNotNull(authors);
        assertEquals(2, authors.size());
        final Author result = authors.get(0);
        assertEquals("authorId2", result.getAuthorId());
        assertEquals("Gogol", result.getAuthorLastname());
        assertEquals("Nikolai", result.getAuthorFirstname());
        final Author result2 = authors.get(1);
        assertEquals("authorId3", result2.getAuthorId());
        assertEquals("Goncharov", result2.getAuthorLastname());
        assertEquals("Ivan", result2.getAuthorFirstname());
    }

    /*
    @Test
    public void listAB() {
        final Author author1 = new Author();
        author1.setAuthorId("authorId1");
        author1.setAuthorLastname("Tolstoi");
        author1.setAuthorFirstname("Lev");
        final Author author2 = new Author();
        author2.setAuthorId("authorId2");
        author2.setAuthorLastname("Gogol");
        author2.setAuthorFirstname("Nikolai");

        final Genre genre = new Genre();
        genre.setGenreId("genreId");
        genre.setGenreName("Rasskaz");

        final Book book1 = new Book();
        book1.setBookId("bookId1");
        book1.setTitle("Tri smerti");
        book1.setBookYear("1996");
        book1.setPublisherId("publisherId1");
        book1.setQuantityPage("35");
        final Book book2 = new Book();
        book2.setBookId("bookId2");
        book2.setTitle("Portret");
        book2.setBookYear("2001");
        book2.setPublisherId("publisherId1");
        book2.setQuantityPage("62");


        genreDao.addGenre(genre);
        bookDao.addBook(book1);
        bookDao.addBook(book2);
        authorDao.addAuthor(author1);
        authorDao.addAuthor(author2);
        bookDao.addAuthorToBook("bookId1", "authorId1");
        bookDao.addAuthorToBook("bookId2", "authorId2");
        bookDao.addGenreToBook("bookId2", "genreId");
        bookDao.addGenreToBook("bookId2", "genreId");

        final List<Author> authors = authorDao.listAuthorBook();

        assertNotNull(authors);
        assertEquals(2, authors.size());

        final Author result1 = authors.get(0);
        assertEquals("Tolstoi", result1.getAuthorLastname());
        assertEquals("Lev", result1.getAuthorFirstname());
        assertEquals("Tri smerti", result1.getTitle());
        assertEquals("Rasskaz", result1.getGenreName());

        final Author result2 = authors.get(0);
        assertEquals("Gogol", result2.getAuthorLastname());
        assertEquals("Nikolai", result2.getAuthorFirstname());
        assertEquals("Portret", result2.getTitle());
        assertEquals("Rasskaz", result2.getGenreName());
    }
    */
}
