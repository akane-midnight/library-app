package ru.vsu.va;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;
import org.skife.jdbi.v2.sqlobject.mixins.Transactional;

import java.util.List;

@RegisterMapper(PublisherMapper.class)
public interface PublisherDao extends Transactional<PublisherDao> {
    @SqlUpdate("INSERT INTO publishers (publisher_id, publisher_name, publisher_city) " +
            "VALUES (:publisherId, :publisherName, :publisherCity)")
    void addPublisher(@BindBean Publisher publisher);

    @SqlQuery("SELECT * FROM publishers")
    List<Publisher> listPublishers();

    /*@SqlQuery("SELECT * FROM publishers WHERE publisher_name = :p_name")
    List<Publisher> listPublishersByName(@Bind("p_name") String pName);*/

    @SqlQuery("SELECT * FROM publishers WHERE publisher_city = :p_city")
    List<Publisher> listPublishersByCity(@Bind("p_city") String pCity);

    @SqlQuery("SELECT * FROM publishers WHERE publisher_name = :p_name")
    Publisher getPublisherByName(@Bind("p_name") String pName);
}
