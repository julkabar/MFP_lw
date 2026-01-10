package yyb.julkabar.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import yyb.julkabar.core.domain.Book;
import yyb.julkabar.core.port.CatalogRepositoryPort;

@Controller
@RequestMapping("/books")
public class BookFormController {

    private final CatalogRepositoryPort bookRepo;

    public BookFormController(CatalogRepositoryPort bookRepo) {
        this.bookRepo = bookRepo;
    }

    @GetMapping("/new")
    public String showAddForm(Model model) {
        model.addAttribute("book", new Book());
        return "book-form";
    }

    @PostMapping
    public String addBook(@ModelAttribute Book book) {

        if (book.getTitle() != null && !book.getTitle().isBlank()) {
            bookRepo.add(book.getTitle(), book.getAuthor(), book.getPubYear());
        }

        return "redirect:/books";
    }
}
