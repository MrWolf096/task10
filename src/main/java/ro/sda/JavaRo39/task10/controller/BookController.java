package ro.sda.JavaRo39.task10.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import ro.sda.JavaRo39.task10.entities.Book;
import ro.sda.JavaRo39.task10.repository.BookRepository;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    BookRepository bookRepository;

    @GetMapping
    public List<Book> getAllBooks(){
        return bookRepository.findAll();
    }

    @GetMapping("/find")
    public List<Book> findTop3ByAutor(@RequestParam(name = "author") String author){
        return bookRepository.findTop3ByAuthorOrderByPagesNumDesc(author);
    }

    @GetMapping("/findByTitle")
    public List<Book> findByTitle(@RequestParam(name = "title", required = false) String title, @RequestParam(name = "pattern", required = false) String pattern){
       if (title!=null && !title.isBlank() ) {
           return bookRepository.findAllByTitle(title);
       }

        if (pattern!=null && !pattern.isBlank() ) {
            return bookRepository.findAllByTitleStartingWith(pattern);
        }
        return List.of();
    }



    @PostMapping
    public Book createBook(@RequestBody Book book){
        bookRepository.save(book);
        return book;
    }
}
