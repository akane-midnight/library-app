package ru.vsu.va;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthorMapper implements ResultSetMapper<Author> {
    public Author map(final int i,
                      final ResultSet resultSet,
                      final StatementContext statementContext) throws SQLException {
        final Author result = new Author();
        result.setAuthorId(resultSet.getString("author_id"));
        result.setAuthorLastname(resultSet.getString("author_lastname"));
        result.setAuthorFirstname(resultSet.getString("author_firstname"));
        return result;
    }
}
