package ru.vsu.va;

import org.junit.Test;

import java.sql.Date;
import java.util.List;

import static org.junit.Assert.*;

public class ReaderDaoTest extends DAOTest {
    private final ReaderDao readerDao = dbi.onDemand(ReaderDao.class);
    private final PublisherDao publisherDao = dbi.onDemand(PublisherDao.class);
    private final BookDao bookDao = dbi.onDemand(BookDao.class);
    private final AuthorDao authorDao = dbi.onDemand(AuthorDao.class);
    private final GiveListDao giveListDao = dbi.onDemand(GiveListDao.class);


    @Test
    public void empty() {
        assertTrue(readerDao.listReaders().isEmpty());
    }

    @Test
    public void addAndList() {
        final Reader reader = new Reader();
        reader.setReaderId("readerId");
        reader.setFirstname("Nik");
        reader.setLastname("Nikonovich");
        reader.setBirthday(Date.valueOf("1996-04-15"));

        readerDao.addReader(reader);

        final List<Reader> readers = readerDao.listReaders();

        assertNotNull(readers);
        assertEquals(1, readers.size());
        final Reader result = readers.get(0);
        assertEquals("readerId", result.getReaderId());
        assertEquals("Nik", result.getFirstname());
        assertEquals("Nikonovich", result.getLastname());
        assertEquals(Date.valueOf("1996-04-15"), result.getBirthday());
    }

    @Test
    public void listReadersByLastname() {
        final Reader reader1 = new Reader();
        reader1.setReaderId("readerId1");
        reader1.setFirstname("Ivan");
        reader1.setLastname("Ivanov");
        reader1.setBirthday(Date.valueOf("1991-07-12"));
        final Reader reader2 = new Reader();
        reader2.setReaderId("readerId2");
        reader2.setFirstname("Nik");
        reader2.setLastname("Nikonovich");
        reader2.setBirthday(Date.valueOf("1996-04-15"));

        readerDao.addReader(reader1);
        readerDao.addReader(reader2);

        final List<Reader> readers = readerDao.listReadersByLastame("Nikonovich");

        assertNotNull(readers);
        assertEquals(1, readers.size());
        final Reader result = readers.get(0);
        assertEquals("readerId2", result.getReaderId());
        assertEquals("Nik", result.getFirstname());
        assertEquals("Nikonovich", result.getLastname());
        assertEquals(Date.valueOf("1996-04-15"), result.getBirthday());
    }

    @Test
    public void listByLetter() {
        final Reader reader1 = new Reader();
        reader1.setReaderId("readerId1");
        reader1.setFirstname("Ivan");
        reader1.setLastname("Ivanov");
        reader1.setBirthday(Date.valueOf("1991-07-12"));
        final Reader reader2 = new Reader();
        reader2.setReaderId("readerId2");
        reader2.setFirstname("Nik");
        reader2.setLastname("Nikonovich");
        reader2.setBirthday(Date.valueOf("1996-04-15"));
        final Reader reader3 = new Reader();
        reader3.setReaderId("readerId3");
        reader3.setFirstname("Andrey");
        reader3.setLastname("Nemov");
        reader3.setBirthday(Date.valueOf("1993-12-07"));


        readerDao.addReader(reader1);
        readerDao.addReader(reader2);
        readerDao.addReader(reader3);

        final List<Reader> readers = readerDao.listReadersByLetter("N%");

        assertNotNull(readers);
        assertEquals(2, readers.size());
        final Reader result1 = readers.get(0);
        assertEquals("readerId2", result1.getReaderId());
        assertEquals("Nik", result1.getFirstname());
        assertEquals("Nikonovich", result1.getLastname());
        assertEquals(Date.valueOf("1996-04-15"), result1.getBirthday());
        final Reader result2 = readers.get(1);
        assertEquals("readerId3", result2.getReaderId());
        assertEquals("Andrey", result2.getFirstname());
        assertEquals("Nemov", result2.getLastname());
        assertEquals(Date.valueOf("1993-12-07"), result2.getBirthday());
    }

    @Test
    public void listReaderBooks(){
        final Reader reader = new Reader();
        reader.setReaderId("readerId");
        reader.setFirstname("Ivan");
        reader.setLastname("Ivanov");
        reader.setBirthday(Date.valueOf("1991-07-12"));

        final Book book1 = new Book();
        book1.setBookId("bookId1");
        book1.setTitle("Alisa v strane chudes");
        book1.setBookYear("2003");
        book1.setQuantityPage(63);
        book1.setPublisherId("pubId");
        final Book book2 = new Book();
        book2.setBookId("bookId2");
        book2.setTitle("Opyat Alisa v zazerkalie");
        book2.setBookYear("2006");
        book2.setQuantityPage(65);
        book2.setPublisherId("pubId");

        final Author author = new Author();
        author.setAuthorId("authorId");
        author.setAuthorLastname("Kerroll");
        author.setAuthorFirstname("L");
        author.setAuthorFirst(true);

        final Publisher publisher = new Publisher();
        publisher.setPublisherId("pubId");
        publisher.setPublisherName("Eksmo");
        publisher.setPublisherCity("Moskva");

        final GiveList giveList1 = new GiveList();
        giveList1.setGiveId("giveId1");
        giveList1.setBookId("bookId1");
        giveList1.setReaderId("readerId");
        giveList1.setGiveDate(Date.valueOf("2017-05-12"));
        giveList1.setReturnDate(Date.valueOf("2017-06-12"));
        final GiveList giveList2 = new GiveList();
        giveList2.setGiveId("giveId2");
        giveList2.setBookId("bookId2");
        giveList2.setReaderId("readerId");
        giveList2.setGiveDate(Date.valueOf("2017-05-27"));
        giveList2.setReturnDate(Date.valueOf("2017-06-27"));

        authorDao.addAuthor(author);
        publisherDao.addPublisher(publisher);
        bookDao.addBook(book1);
        bookDao.addBook(book2);
        readerDao.addReader(reader);
        bookDao.addAuthorToBook("bookId1", "authorId");
        bookDao.addAuthorToBook("bookId2", "authorId");
        giveListDao.openGiveNote(giveList1);
        giveListDao.openGiveNote(giveList2);
        giveListDao.closeGiveNote("giveId2", Date.valueOf("2017-06-13"));

        final List<GiveNoteReader> giveNotes = readerDao.listReaderBooks("readerId");

        assertNotNull(giveNotes);
        assertEquals(2, giveNotes.size());
        final GiveNoteReader result1 = giveNotes.get(0);
        assertEquals("giveId1", result1.getGiveId());
        assertEquals("Alisa v strane chudes", result1.getTitle());
        assertEquals("Kerroll", result1.getAuthorName());
        assertEquals(Date.valueOf("2017-05-12"), result1.getGiveDate());
        assertEquals(Date.valueOf("2017-06-12"), result1.getReturnDate());
        final GiveNoteReader result2 = giveNotes.get(1);
        assertEquals("giveId2", result2.getGiveId());
        assertEquals("Opyat Alisa v zazerkalie", result2.getTitle());
        assertEquals("Kerroll", result2.getAuthorName());
        assertEquals(Date.valueOf("2017-05-27"), result2.getGiveDate());
        assertEquals(Date.valueOf("2017-06-27"), result2.getReturnDate());
        assertEquals(Date.valueOf("2017-06-13"), result2.getRealReturnDate());
    }

}
