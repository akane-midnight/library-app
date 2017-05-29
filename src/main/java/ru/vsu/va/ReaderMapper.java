package ru.vsu.va;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReaderMapper implements ResultSetMapper<Reader> {
    public Reader map(final int i,
                      final ResultSet resultSet,
                      final StatementContext statementContext) throws SQLException {
        final Reader result = new Reader();
        result.setReaderId(resultSet.getString("reader_id"));
        result.setLastname(resultSet.getString("lastname"));
        result.setFirstname(resultSet.getString("firstname"));
        result.setBirthday(resultSet.getDate("birthday"));
        return result;
    }
}
