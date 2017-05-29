package ru.vsu.va;

import org.skife.jdbi.v2.DBI;

import java.util.List;

public class PublishersService {
    private final DBI dbi;
    private final IdGenerator idGenerator;

    @Inject
    public PublishersService(final DBI dbi, final IdGenerator idGenerator) {
        this.dbi = dbi;
        this.idGenerator = idGenerator;
    }

    public void addPublisher(final String pubName,
                             final String pubCity){
        final PublisherDao publisherDao = dbi.onDemand(PublisherDao.class);

        if (publisherDao.getPublisherByName(pubName)!= null) {
            throw new IllegalArgumentException("Publisher yet presented in DB");
        }
        else {
            final String publisherId = idGenerator.generateId();

            final Publisher publisher = new Publisher();
            publisher.setPublisherId(publisherId);
            publisher.setPublisherName(pubName);
            publisher.setPublisherCity(pubCity);
            publisherDao.addPublisher(publisher);
        }
    }

    public List<Publisher> listAllPublishers() {
        final PublisherDao publisherDao = dbi.onDemand(PublisherDao.class);
        return publisherDao.listPublishers();
    }

    public List<Publisher> listPublishersByCity(final String pubCity) {
        final PublisherDao publisherDao = dbi.onDemand(PublisherDao.class);
        return publisherDao.listPublishersByCity(pubCity);
    }

    public Publisher getPublisherByName(final String pubName) {
        final PublisherDao publisherDao = dbi.onDemand(PublisherDao.class);
        return publisherDao.getPublisherByName(pubName);
    }
}
