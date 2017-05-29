package ru.vsu.va;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.LiquibaseException;
import liquibase.resource.ClassLoaderResourceAccessor;
import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.Handle;

import java.sql.Connection;
import java.sql.SQLException;

public class MainModule extends AbstractModule {
    @Override
    protected void configure() {

    }

    @Provides
    public DBI provideDbi() throws SQLException, LiquibaseException {
        final String DB_URL = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1";
        final DBI dbi = new DBI(DB_URL);

        final Handle handle = dbi.open();
        final Connection connection = handle.getConnection();
        final Database database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(new JdbcConnection(connection));
        final Liquibase liquibase = new Liquibase("migrations.xml", new ClassLoaderResourceAccessor(), database);
        liquibase.dropAll();
        liquibase.update("");
        handle.close();

        return dbi;
    }
}
