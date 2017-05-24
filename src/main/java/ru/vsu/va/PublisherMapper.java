package ru.vsu.va;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PublisherMapper implements ResultSetMapper<Publisher> {
    public Publisher map(final int i,
                         final ResultSet resultSet,
                         final StatementContext statementContext) throws SQLException {
        final Publisher result = new Publisher();
        result.setPublisherId(resultSet.getString("publisher_id"));
        result.setPublisherName(resultSet.getString("publisher_name"));
        result.setPublisherCity(resultSet.getString("publisher_city"));
        return result;
    }
}
