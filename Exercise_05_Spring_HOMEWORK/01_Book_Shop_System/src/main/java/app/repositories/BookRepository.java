package app.repositories;

import app.entities.Author;
import app.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>
{
    List<Book> findByReleaseDateAfter(Date date);

    @Query("select b from books as b join authors a on b.author.id =a.id where a.firstName ='George' and a.lastName ='Powell' order by b.releaseDate desc, b.title asc ")
    List<Book> findBookByGeorgePowell();
}
