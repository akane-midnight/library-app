package ru.vsu.va;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class PublisherDaoTest extends DAOTest{
    private final PublisherDao publisherDao = dbi.onDemand(PublisherDao.class);

    @Test
    public void empty() {
        assertTrue(publisherDao.listPublishers().isEmpty());
    }

    @Test
    public void addAndList() {
        final Publisher publisher = new Publisher();
        publisher.setPublisherId("pubId");
        publisher.setPublisherName("Eksmo");
        publisher.setPublisherCity("Moskva");

        publisherDao.addPublisher(publisher);

        final List<Publisher> publishers = publisherDao.listPublishers();

        assertNotNull(publishers);
        assertEquals(1, publishers.size());
        final Publisher result = publishers.get(0);
        assertEquals("pubId", result.getPublisherId());
        assertEquals("Eksmo", result.getPublisherName());
        assertEquals("Moskva", result.getPublisherCity());
    }
    @Test
    public void listByCity() {
        final Publisher publisher1 = new Publisher();
        publisher1.setPublisherId("pubId1");
        publisher1.setPublisherName("Eksmo");
        publisher1.setPublisherCity("Moskva");
        final Publisher publisher2 = new Publisher();
        publisher2.setPublisherId("pubId2");
        publisher2.setPublisherName("Lenizdat");
        publisher2.setPublisherCity("SPb");
        final Publisher publisher3 = new Publisher();
        publisher3.setPublisherId("pubI3d");
        publisher3.setPublisherName("Istari");
        publisher3.setPublisherCity("Moskva");

        publisherDao.addPublisher(publisher1);
        publisherDao.addPublisher(publisher2);
        publisherDao.addPublisher(publisher3);

        final List<Publisher> publishers = publisherDao.listPublishersByCity("Moskva");

        assertNotNull(publishers);
        assertEquals(2, publishers.size());
        final Publisher result = publishers.get(0);
        assertEquals("pubId1", result.getPublisherId());
        assertEquals("Eksmo", result.getPublisherName());
        assertEquals("Moskva", result.getPublisherCity());
        assertEquals("pubId3", result.getPublisherId());
        assertEquals("Istari", result.getPublisherName());
        assertEquals("Moskva", result.getPublisherCity());
    }

    /*@Test
    public void listByName() {
        final Publisher publisher1 = new Publisher();
        publisher1.setPublisherId("pubId1");
        publisher1.setPublisherName("Eksmo");
        publisher1.setPublisherCity("Moskva");
        final Publisher publisher2 = new Publisher();
        publisher2.setPublisherId("pubId2");
        publisher2.setPublisherName("Lenizdat");
        publisher2.setPublisherCity("SPb");
        final Publisher publisher3 = new Publisher();
        publisher3.setPublisherId("pubI3d");
        publisher3.setPublisherName("Istari");
        publisher3.setPublisherCity("Moskva");

        publisherDao.addPublisher(publisher1);
        publisherDao.addPublisher(publisher2);
        publisherDao.addPublisher(publisher3);

        final List<Publisher> publishers = publisherDao.listPublishersByName("Lenizdat");

        assertNotNull(publishers);
        assertEquals(1, publishers.size());
        final Publisher result = publishers.get(0);
        assertEquals("pubId2", result.getPublisherId());
        assertEquals("Lenizdat", result.getPublisherName());
        assertEquals("SPb", result.getPublisherCity());
    }*/


}
