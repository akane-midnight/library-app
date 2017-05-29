package ru.vsu.va;

import org.skife.jdbi.v2.DBI;

import java.util.Date;
import java.util.List;

public class ReadersService {
    private final DBI dbi;
    private final IdGenerator idGenerator;

    @Inject
    public ReadersService(final DBI dbi, final IdGenerator idGenerator) {
        this.dbi = dbi;
        this.idGenerator = idGenerator;
    }


    public void addReader(final String lastname,
                          final String firstname,
                          final Date birthday){
        final ReaderDao readerDao = dbi.onDemand(ReaderDao.class);

        if (readerDao.getReader(lastname, firstname)!= null) {
            throw new IllegalArgumentException("Reader yet presented in DB");
        }
        else {
            final String readerId = idGenerator.generateId();

            final Reader reader = new Reader();
            reader.setReaderId(readerId);
            reader.setLastname(lastname);
            reader.setFirstname(firstname);
            reader.setBirthday(birthday); //пофиксить
            readerDao.addReader(reader);
        }
    }

    public List<Reader> listReadersByLastname(final String lastname) {
        final ReaderDao readerDao = dbi.onDemand(ReaderDao.class);
        return readerDao.listReadersByLastame(lastname);
    }

    public List<Reader> listReadersByLetter(final String letter) { //символ вместо строки
        final ReaderDao readerDao = dbi.onDemand(ReaderDao.class);
        return readerDao.listReadersByLetter(letter);
    }

    public List<GiveNoteReader> listReaderTakes(final String readerId) {
        final ReaderDao readerDao = dbi.onDemand(ReaderDao.class);
        return readerDao.listReaderBooks(readerId);
    }
}
