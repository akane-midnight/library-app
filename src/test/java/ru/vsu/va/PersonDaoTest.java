package ru.vsu.va;

import org.junit.Test;

import java.sql.Date;
import java.util.List;

import static org.junit.Assert.*;

public class PersonDaoTest extends DAOTest {
    private final PersonDao personDao = dbi.onDemand(PersonDao.class);

    @Test
    public void empty() {
        assertTrue(personDao.listPersons().isEmpty());
    }

    @Test
    public void addAndList() {
        final Person person = new Person();
        person.setPersonId("personId");
        person.setFirstname("Nik");
        person.setLastname("Nikonovich");
        person.setBirthday(Date.valueOf("1996-04-15"));

        personDao.addPerson(person);

        final List<Person> persons = personDao.listPersons();

        assertNotNull(persons);
        assertEquals(1, persons.size());
        final Person result = persons.get(0);
        assertEquals("personId", result.getPersonId());
        assertEquals("Nik", result.getFirstname());
        assertEquals("Nikonovich", result.getLastname());
        assertEquals(Date.valueOf("1996-04-15"), result.getBirthday());
    }

    @Test
    public void listPersonByLastname() {
        final Person person1 = new Person();
        person1.setPersonId("personId1");
        person1.setFirstname("Ivan");
        person1.setLastname("Ivanov");
        person1.setBirthday(Date.valueOf("1991-07-12"));
        final Person person2 = new Person();
        person2.setPersonId("personId2");
        person2.setFirstname("Nik");
        person2.setLastname("Nikonovich");
        person2.setBirthday(Date.valueOf("1996-04-15"));

        personDao.addPerson(person1);
        personDao.addPerson(person2);

        final List<Person> persons = personDao.listPersonByLastame("Nikonovich");

        assertNotNull(persons);
        assertEquals(1, persons.size());
        final Person result = persons.get(0);
        assertEquals("personId2", result.getPersonId());
        assertEquals("Nik", result.getFirstname());
        assertEquals("Nikonovich", result.getLastname());
        assertEquals(Date.valueOf("1996-04-15"), result.getBirthday());
    }

    @Test
    public void listByLetter() {
        final Person person1 = new Person();
        person1.setPersonId("personId1");
        person1.setFirstname("Ivan");
        person1.setLastname("Ivanov");
        person1.setBirthday(Date.valueOf("1991-07-12"));
        final Person person2 = new Person();
        person2.setPersonId("personId2");
        person2.setFirstname("Nik");
        person2.setLastname("Nikonovich");
        person2.setBirthday(Date.valueOf("1996-04-15"));
        final Person person3 = new Person();
        person3.setPersonId("personId3");
        person3.setFirstname("Andrey");
        person3.setLastname("Nemov");
        person3.setBirthday(Date.valueOf("1993-12-07"));


        personDao.addPerson(person1);
        personDao.addPerson(person2);
        personDao.addPerson(person3);

        final List<Person> persons = personDao.listPersonByLetter("N%");

        assertNotNull(persons);
        assertEquals(2, persons.size());
        final Person result1 = persons.get(0);
        assertEquals("personId2", result1.getPersonId());
        assertEquals("Nik", result1.getFirstname());
        assertEquals("Nikonovich", result1.getLastname());
        assertEquals(Date.valueOf("1996-04-15"), result1.getBirthday());
        final Person result2 = persons.get(1);
        assertEquals("personId3", result2.getPersonId());
        assertEquals("Andrey", result2.getFirstname());
        assertEquals("Nemov", result2.getLastname());
        assertEquals(Date.valueOf("1993-12-07"), result2.getBirthday());
    }

}
