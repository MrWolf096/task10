package ro.sda.JavaRo39.task10.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ro.sda.JavaRo39.task10.entities.Author;
import ro.sda.JavaRo39.task10.entities.Book;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Long> {

    List<Author> findAllById(Long id);

    List<Author> findAllByName(String name);

    Optional<Author> findAllAuthorsByAge(int age);

    Optional<Author> findAllAuthorsByCountry(String country);


}
