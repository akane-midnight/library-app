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
}
