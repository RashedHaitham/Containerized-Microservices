package com.example.enterBook.controllers;

import com.example.enterBook.model.Book;
import com.example.enterBook.service.AnalyticsService;
import com.example.enterBook.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class BookController {

    private final BookService bookService;
    private final AnalyticsService analyticsService;

    @Autowired
    public BookController(BookService bookService, AnalyticsService analyticsService){
        this.bookService = bookService;
        this.analyticsService = analyticsService;
    }

    @GetMapping("/addBook")
    public String showVideoGameForm(Model model) {
        model.addAttribute("book", new Book());
        return "add_book";
    }

    @PostMapping("/addBook")
    public String addVideoGame(Book book, RedirectAttributes redirectAttributes) {
        bookService.addBook(book);
        analyticsService.notifyNewBookEntry();
        redirectAttributes.addFlashAttribute("success", "New Book has been added!");
        return "redirect:/addBook";
    }
}
