package ru.vsu.va;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;
import org.skife.jdbi.v2.sqlobject.mixins.Transactional;

import java.util.List;

@RegisterMapper(PersonMapper.class)
public interface PersonDao extends Transactional<PersonDao> {
    @SqlUpdate("INSERT INTO persons (person_id, firstname, lastname, birthday) " +
            "VALUES (:personId, :firstname, :lastname, :birthday)")
    void addPerson(@BindBean Person person);

    @SqlQuery("SELECT * FROM persons")
    List<Person> listPersons();

    @SqlQuery("SELECT * FROM persons WHERE lastname = :name")
    List<Person> listPersonByLastame(@Bind("name") String name);

    @SqlQuery("SELECT * FROM persons WHERE lastname LIKE :letter")
    List<Person> listPersonByLetter(@Bind("letter") String letter);

    @SqlQuery("SELECT * FROM persons WHERE birthday = :date")
    List<Person> listPersonByBirthday(@Bind("date") String date);
}
