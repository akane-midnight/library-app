package ru.vsu.va;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GenreMapper implements ResultSetMapper<Genre> {
    public Genre map(final int i,
                     final ResultSet resultSet,
                     final StatementContext statementContext) throws SQLException {
        final Genre result = new Genre();
        result.setGenreId(resultSet.getString("genre_id"));
        result.setGenreName(resultSet.getString("genre_name"));
        return result;
    }
}
