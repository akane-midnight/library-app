package ru.vsu.va;

import org.skife.jdbi.v2.DBI;

import java.util.Date;
import java.util.List;

public class GiveListService {
    private final DBI dbi;
    private final IdGenerator idGenerator;

    @Inject
    public GiveListService(final DBI dbi, final IdGenerator idGenerator) {
        this.dbi = dbi;
        this.idGenerator = idGenerator;
    }

    public void createGiveNote(final String bookId,
                               final String readerId,
                               final Date giveDate,
                               final Date returnDate){
        final GiveListDao giveListDao = dbi.onDemand(GiveListDao.class);

            final String giveId = idGenerator.generateId();

            final GiveList giveList = new GiveList();
            giveList.setGiveId(giveId);
            giveList.setBookId(bookId);
            giveList.setReaderId(readerId);
            giveList.setGiveDate(giveDate);
            giveList.setReturnDate(returnDate);  //пофиксить
            giveListDao.openGiveNote(giveList);
    }

    public void closeGiveNote(final String giveId, final Date realReturnDate){
        final GiveListDao giveListDao = dbi.onDemand(GiveListDao.class);
        giveListDao.closeGiveNote(giveId, realReturnDate);
    }

    public List<GiveNoteDate> listGivesByDate(final Date giveDate) {
        final GiveListDao giveListDao = dbi.onDemand(GiveListDao.class);
        return giveListDao.listGiveListByDate(giveDate);
    }
}
