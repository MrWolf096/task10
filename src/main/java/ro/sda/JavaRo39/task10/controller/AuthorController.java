package ro.sda.JavaRo39.task10.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import ro.sda.JavaRo39.task10.entities.Author;
import ro.sda.JavaRo39.task10.entities.Book;
import ro.sda.JavaRo39.task10.repository.AuthorRepository;

import java.util.List;

@RestController
@RequestMapping("/author")
public class AuthorController {

    @Autowired
    AuthorRepository authorRepository;

    @GetMapping
    public List<Author> getAllAuthors(){
        return authorRepository.findAll();
    }



}
