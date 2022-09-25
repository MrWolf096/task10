package ro.sda.JavaRo39.task10.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import ro.sda.JavaRo39.task10.entities.Book;
import ro.sda.JavaRo39.task10.exceptions.EntityNotFoundException;
import ro.sda.JavaRo39.task10.repository.BookRepository;
//model view controller = mvc

@Controller
@RequestMapping("/books")
@AllArgsConstructor
@Slf4j
public class MVCBookController {
    BookRepository bookRepository;

    @GetMapping
    public String showAllBooks(ModelMap modelMap){
        modelMap.addAttribute("booksList",bookRepository.findAll());
        return "displayBooks";
    }

    @GetMapping("/create")
    public String createForm(ModelMap modelMap){
        modelMap.addAttribute("formObject",new Book());
        return "createBook";
    }
    @PostMapping("/create")
    public RedirectView createBook(@ModelAttribute("formObject") Book book, ModelMap modelMap){
        bookRepository.save(book);
        return new RedirectView("/books");
    }

    @GetMapping("/edit/{bookId}")
    public String editBooks(@PathVariable("bookId") long id, ModelMap modelMap){

        Book book =bookRepository.findById(id)
                .orElseThrow(()->new EntityNotFoundException(String.format("Book with id %d does not exists",id)));
        modelMap.addAttribute("formObject",book);
        return "createBook";
    }

    @GetMapping("/delete/{bookId}")
    public String deleteBooks(@PathVariable("bookId") long id, ModelMap modelMap){
        Book book = bookRepository.findById(id)
                .orElseThrow(()->new EntityNotFoundException(String.format("Book with the id: %d does not exist",id)));
        modelMap.addAttribute("formObject",book);
        bookRepository.deleteById(id);
        return "redirect:/books";
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public String handlerException(EntityNotFoundException exception){
        log.error(exception.getMessage(),exception);
        return "customError";
    }
}
