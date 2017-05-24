package ru.vsu.va;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookMapper implements ResultSetMapper<Book> {
    public Book map(final int i,
                    final ResultSet resultSet,
                    final StatementContext statementContext) throws SQLException {
        final Book result = new Book();
        result.setBookId(resultSet.getString("book_id"));
        result.setTitle(resultSet.getString("title"));
        result.setBookYear(resultSet.getString("book_year"));
        result.setQuantityPage(resultSet.getString("quantity_page"));
        result.setPublisherId(resultSet.getString("publisher_id"));
        return result;
    }
}
