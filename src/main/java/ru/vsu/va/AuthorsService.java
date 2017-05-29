package ru.vsu.va;


import org.skife.jdbi.v2.DBI;

import java.util.List;

public class AuthorsService {

    private final DBI dbi;
    private final IdGenerator idGenerator;

    @Inject
    public AuthorsService(final DBI dbi, final IdGenerator idGenerator) {
        this.dbi = dbi;
        this.idGenerator = idGenerator;
    }

    public void addAuthor(final String lastname,
                          final String firstname){
        final AuthorDao authorDao = dbi.onDemand(AuthorDao.class);

            if (authorDao.getAuthorByLastname(lastname)!= null) {
                throw new IllegalArgumentException("Author yet presented in DB");
            }
            else {
                final String authorId = idGenerator.generateId();

                final Author author = new Author();
                author.setAuthorId(authorId);
                author.setAuthorLastname(lastname);
                author.setAuthorFirstname(firstname);
                authorDao.addAuthor(author);
            }

    }

    public List<Author> listAllAuthors() {
        final AuthorDao authorDao = dbi.onDemand(AuthorDao.class);
        return authorDao.listAuthors();
    }

    public List<Author> listAuthorsByLetter(final String letter) { //символ вместо строки
        final AuthorDao authorDao = dbi.onDemand(AuthorDao.class);
        return authorDao.listAuthorsByLetter(letter);
    }

}
