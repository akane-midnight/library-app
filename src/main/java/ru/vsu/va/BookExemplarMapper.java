package ru.vsu.va;


import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

public class BookExemplarMapper implements ResultSetMapper<BookExemplar> {
    public BookExemplar map(final int i,
                    final ResultSet resultSet,
                    final StatementContext statementContext) throws SQLException {
        final BookExemplar result = new BookExemplar();
        result.setBookExemplarId(resultSet.getString("book_exemplar_id"));
        result.setBookDescription(resultSet.getString("book_description"));
        result.setBookId(resultSet.getString("book_id"));
        return result;
    }
}
