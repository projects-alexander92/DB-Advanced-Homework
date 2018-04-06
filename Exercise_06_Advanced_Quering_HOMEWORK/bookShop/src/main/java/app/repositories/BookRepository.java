package app.repositories;

import app.entities.Author;
import app.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>
{
    List<Book> getAllByAgeRestriction(int ageRestriction);

    @Query("select b from books as b where b.editionType = 2 and b.copies < 500")
    List<Book> getAllGoldenBooksWithLessThen500Copies();

    @Query("select b from books as b where b.price < 5 or b.price > 40")
    List<Book> getByPriceInBoundaries();

    @Query("select b from books as b where b.releaseDate <> :date")
    List<Book> notRealeasedBooks(@Param(value = "date") Date date);

    @Query("select b from books as b where b.releaseDate < :date")
    List<Book> realeasedBeforeDate(@Param(value = "date") Date date);

    List<Book> getAllByTitleContaining(String value);

    @Query("select b from books as b where b.author.lastName like :starsWith")
    List<Book> bookTitlesSearch(@Param(value = "starsWith") String startsWith);

    @Query("select count(b.title) from books as b where length(b.title) > :lengthOfTitle")
    int getBookTitleLenght(@Param(value = "lengthOfTitle") int lengthOfTitle);

    @Query("select b.title,b.editionType,b.ageRestriction,b.price from books as b where b.title = :bookTitle")
    List<Object[]> selectBookInfo(@Param(value = "bookTitle") String bookTitle);

    @Modifying
    @Transactional
    @Query("update books b set b.copies = b.copies + :copiesCount where b.releaseDate > :releaseDate")
    int increaseBookCopies(@Param(value = "copiesCount") int copiesCount, @Param(value = "releaseDate") Date releaseDate);

    int deleteByCopiesLessThan(int value);

}
