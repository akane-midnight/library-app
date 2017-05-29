package ru.vsu.va;

import org.skife.jdbi.v2.DBI;

import java.util.List;

public class GenresService {

    private final DBI dbi;
    private final IdGenerator idGenerator;

    @Inject
    public GenresService(final DBI dbi, final IdGenerator idGenerator) {
        this.dbi = dbi;
        this.idGenerator = idGenerator;
    }

    public void addGenre(final String genreName){
        final GenreDao genreDao = dbi.onDemand(GenreDao.class);

        if (genreDao.getGenreByName(genreName)!= null) {
            throw new IllegalArgumentException("Genre yet presented in DB");
        }
        else {
            final String genreId = idGenerator.generateId();

            final Genre genre = new Genre();
            genre.setGenreId(genreId);
            genre.setGenreName(genreName);
            genreDao.addGenre(genre);
        }
    }

    public List<Genre> listAllGenres() {
        final GenreDao genreDao = dbi.onDemand(GenreDao.class);
        return genreDao.listGenres();
    }
}
