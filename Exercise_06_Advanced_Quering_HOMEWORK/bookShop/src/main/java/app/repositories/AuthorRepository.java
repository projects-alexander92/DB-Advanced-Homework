package app.repositories;

import app.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long>
{
    @Query("select a from authors as a join books as b on b.id = a.id and b.releaseDate < '1999-00-00' group by a")
    List<Author> findAuthorsWithBooksBefore1990();

    @Query("select a from authors as a order by a.books.size desc ")
    List<Author> findAllAuthorsOrdered();

    @Query("select a from authors as a where a.firstName like :endString")
    List<Author> getByFirstNameEndingWith(@Param(value = "endString") String endString);

    //SELECT a.first_name,a.last_name, sum(b.copies) FROM authors as a JOIN books b ON a.id = b.auhtor_id GROUP BY auhtor_id
    @Query("select a.firstName, a.lastName, sum(b.copies) from authors as a join books b on b.author.id = a.id group by a.id order by sum(b.copies) desc")
    List<Object[]> getTotalBookCopiesByAuthor();
}
