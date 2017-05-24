package ru.vsu.va;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GiveListMapper implements ResultSetMapper<GiveList> {
    public GiveList map(final int i,
                        final ResultSet resultSet,
                        final StatementContext statementContext) throws SQLException {
        final GiveList result = new GiveList();
        result.setGiveId(resultSet.getString("give_id"));
        result.setBookId(resultSet.getString("book_id"));
        result.setPersonId(resultSet.getString("person_id"));
        result.setGiveDate(resultSet.getDate("give_date"));
        result.setReturnDate(resultSet.getDate("return_date"));
        result.setRealReturnDate(resultSet.getDate("real_return_date"));
        return result;
    }
}
