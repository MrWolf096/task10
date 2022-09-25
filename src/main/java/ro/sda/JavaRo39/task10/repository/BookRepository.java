package ro.sda.JavaRo39.task10.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ro.sda.JavaRo39.task10.entities.Book;

import java.util.List;
import java.util.Optional;


public interface BookRepository extends JpaRepository <Book, Long>{

     List<Book> findAllByTitle(String title);

     Optional<Book> findAllBookByIsbn(String isbn);

     Optional<Book> findBookByAuthorAndIsbn(String author , String isbn);

     List<Book> findTop3ByAuthorOrderByPagesNumDesc(String author);

     List<Book> findAllByTitleStartingWith(String titlePattern);

     List<Book> findAllByPagesNumBetween(int start , int end);

     @Query(value = "SELECT f FROM Book f WHERE f.pagesNum >=:num" , nativeQuery = false)
     List<Book> findWherePagesNumIsGreaterThanX(@Param("num") Integer num);
}
