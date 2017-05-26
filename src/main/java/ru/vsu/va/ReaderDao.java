package ru.vsu.va;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;
import org.skife.jdbi.v2.sqlobject.mixins.Transactional;

import java.util.List;

@RegisterMapper(ReaderMapper.class)
public interface ReaderDao extends Transactional<ReaderDao> {
    @SqlUpdate("INSERT INTO readers (reader_id, firstname, lastname, birthday) " +
            "VALUES (:readerId, :firstname, :lastname, :birthday)")
    void addReader(@BindBean Reader reader);

    @SqlQuery("SELECT * FROM readers")
    List<Reader> listReaders();

    @SqlQuery("SELECT * FROM readers WHERE lastname = :name")
    List<Reader> listReadersByLastame(@Bind("name") String name);

    @SqlQuery("SELECT * FROM readers WHERE lastname LIKE :letter")
    List<Reader> listReadersByLetter(@Bind("letter") String letter);

    @SqlQuery("SELECT * FROM readers WHERE birthday = :date")
    List<Reader> listReadersByBirthday(@Bind("date") String date);
}
