package ru.vsu.va;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;
import org.skife.jdbi.v2.sqlobject.mixins.Transactional;

import java.util.List;

@RegisterMapper(GenreMapper.class)
public interface GenreDao extends Transactional<GenreDao> {
    @SqlUpdate("INSERT INTO genres (genre_id, genre_name) " +
            "VALUES (:genreId, :genreName)")
    void addGenre(@BindBean Genre genre);

    @SqlQuery("SELECT * FROM genres")
    List<Genre> listGenres();


    @SqlQuery("SELECT * FROM genress WHERE genre_id = :genreId")
    Author getGenre(@Bind("genreId") String genreId);

}
