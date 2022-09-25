package ro.sda.JavaRo39.task10.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import ro.sda.JavaRo39.task10.entities.Author;
import ro.sda.JavaRo39.task10.exceptions.EntityNotFoundException;
import ro.sda.JavaRo39.task10.repository.AuthorRepository;

@Controller
@RequestMapping("/authors")
@AllArgsConstructor
@Slf4j
public class MVCAuthorController {

    AuthorRepository authorRepository;

    @GetMapping
    public String showAllAuthors(ModelMap modelMap){
        modelMap.addAttribute("authorList",authorRepository.findAll());
        return "displayAuthors";
    }

    @GetMapping("/create")
    public String createAuthor(ModelMap modelMap){
        modelMap.addAttribute("formObject",new Author());
        return "createAuthor";
    }
    @PostMapping("/create")
    public RedirectView createAuthor(@ModelAttribute("formObject") Author author, ModelMap modelMap){
        authorRepository.save(author);
        return new RedirectView("/authors");
    }

    @GetMapping("/edit/{authorId}")
    public String editAuthors(@PathVariable("authorId") long id, ModelMap modelMap){

        Author author = authorRepository.findById(id)
                .orElseThrow(()->new EntityNotFoundException(String.format("The author with id %d does not exists",id)));
        modelMap.addAttribute("formObject",author);
        return "createAuthor";
    }

    @GetMapping("/delete/{authorId}")
    public String deleteAuthors(@PathVariable("authorId") long id, ModelMap modelMap){
        Author author = authorRepository.findById(id)
                .orElseThrow(()->new EntityNotFoundException(String.format("The author with the id: %d does not exist",id)));
        modelMap.addAttribute("formObject",author);
        authorRepository.deleteById(id);
        return "redirect:/authors";
    }

}
