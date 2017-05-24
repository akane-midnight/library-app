package ru.vsu.va;

import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.LiquibaseException;
import liquibase.resource.ClassLoaderResourceAccessor;
import org.junit.Before;
import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.Handle;

import java.sql.Connection;

public class DAOTest {
    private static final String DB_URL = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1";
    protected static final DBI dbi = new DBI(DB_URL);

    @Before
    public void setup() throws LiquibaseException {
        final Handle handle = dbi.open();
        final Connection connection = handle.getConnection();
        final Database database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(new JdbcConnection(connection));
        final Liquibase liquibase = new Liquibase("migrations.xml", new ClassLoaderResourceAccessor(), database);
        liquibase.dropAll();
        liquibase.update("test");
        handle.close();
    }
}