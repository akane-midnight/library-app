package ru.vsu.va;

import org.junit.Test;

import java.sql.Date;
import java.util.List;

import static org.junit.Assert.*;

public class GiveListDaoTest extends DAOTest {
    private final GiveListDao giveListDao = dbi.onDemand(GiveListDao.class);
    private final BookDao bookDao = dbi.onDemand(BookDao.class);
    private final PersonDao personDao = dbi.onDemand(PersonDao.class);
    private final AuthorDao authorDao = dbi.onDemand(AuthorDao.class);
    private final PublisherDao publisherDao = dbi.onDemand(PublisherDao.class);


    @Test
    public void empty() {
        assertTrue(giveListDao.listGiveList().isEmpty());
    }

    @Test
    public void addAndList() {
        final GiveList giveList = new GiveList();
        giveList.setGiveId("giveId");
        giveList.setBookId("bookId");
        giveList.setPersonId("personId");
        giveList.setGiveDate(Date.valueOf("2017-05-12"));
        giveList.setReturnDate(Date.valueOf("2017-06-12"));
        //giveList.setRealReturnDate(Date.valueOf("1991-07-12"));

        final Book book = new Book();
        book.setBookId("bookId");
        book.setTitle("Voina i mir");
        book.setBookYear("1998");
        book.setQuantityPage("1603");
        book.setPublisherId("pubId");

        final Person person = new Person();
        person.setPersonId("personId");
        person.setFirstname("Nik");
        person.setLastname("Nikonovich");
        person.setBirthday(Date.valueOf("1996-04-15"));

        final Author author = new Author();
        author.setAuthorId("authorId");
        author.setAuthorLastname("Tolstoi");
        author.setAuthorFirstname("Lev");
        author.setAuthorFirst(true);

        final Publisher publisher = new Publisher();
        publisher.setPublisherId("pubId");
        publisher.setPublisherName("Eksmo");
        publisher.setPublisherCity("Moskva");

        publisherDao.addPublisher(publisher);
        bookDao.addBook(book);
        authorDao.addAuthor(author);
        bookDao.addAuthorToBook("bookId", "authorId");
        personDao.addPerson(person);
        giveListDao.addInGiveList(giveList);

        final List<GiveNote> giveNotes = giveListDao.listGiveList();

        assertNotNull(giveNotes);
        assertEquals(1, giveNotes.size());
        final GiveNote result = giveNotes.get(0);
        assertEquals("giveId", result.getGiveId());
        assertEquals("Voina i mir", result.getTitle());
        assertEquals("Tolstoi", result.getAuthorName());
        assertEquals("Nikonovich", result.getReaderLastname());
        assertEquals("Nik", result.getReaderFirstname());
        assertEquals(Date.valueOf("2017-05-12"), result.getGiveDate());
        assertEquals(Date.valueOf("2017-06-12"), result.getReturnDate());
    }

}
