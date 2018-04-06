package app.services.implementations;

import app.entities.Author;
import app.repositories.AuthorRepository;
import app.services.interfaces.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService
{
    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository)
    {
        this.authorRepository = authorRepository;
    }

    @Override
    public Author findById(long id)
    {
        return this.authorRepository.findById(id).get();
    }

    @Override
    public void saveAuthor(Author author)
    {
        this.authorRepository.save(author);
    }

    @Override
    public List<Author> getAll()
    {
        return (List<Author>) this.authorRepository.findAll();
    }

    @Override
    public List<Author> getAllWithBookBefore1990()
    {
        return this.authorRepository.findAuthorsWithBooksBefore1990();
    }

    @Override
    public List<Author> getAllAuthorsOrderByNumberOfBooks()
    {
        return this.authorRepository.findAllAuthorsOrdered();
    }

}
