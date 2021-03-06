package ru.vsu.va;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GiveNoteDateMapper implements ResultSetMapper<GiveNoteDate> {
    public GiveNoteDate map(final int i,
                        final ResultSet resultSet,
                        final StatementContext statementContext) throws SQLException {
        final GiveNoteDate result = new GiveNoteDate();
        result.setGiveId(resultSet.getString("give_id"));
        result.setTitle(resultSet.getString("title"));
        result.setAuthorName(resultSet.getString("author_lastname"));
        result.setReaderLastname(resultSet.getString("lastname"));
        result.setReaderFirstname(resultSet.getString("firstname"));
        result.setGiveDate(resultSet.getDate("give_date"));
        result.setReturnDate(resultSet.getDate("return_date"));
        result.setRealReturnDate(resultSet.getDate("real_return_date"));
        return result;
    }
}
