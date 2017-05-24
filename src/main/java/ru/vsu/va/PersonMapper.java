package ru.vsu.va;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonMapper implements ResultSetMapper<Person> {
    public Person map(final int i,
                      final ResultSet resultSet,
                      final StatementContext statementContext) throws SQLException {
        final Person result = new Person();
        result.setPersonId(resultSet.getString("person_id"));
        result.setFirstname(resultSet.getString("firstname"));
        result.setLastname(resultSet.getString("lastname"));
        result.setBirthday(resultSet.getDate("birthday"));
        return result;
    }
}
