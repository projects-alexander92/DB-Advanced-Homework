package app.repositories;

import app.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long>
{
    @Query("select a from authors as a join books as b on b.id = a.id and b.releaseDate < '1999-00-00' group by a")
    List<Author> findAuthorsWithBooksBefore1990();

    @Query("select a from authors as a order by a.books.size desc ")
    List<Author> findAllAuthorsOrdered();
}
