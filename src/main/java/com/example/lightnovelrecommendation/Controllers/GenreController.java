package com.example.lightnovelrecommendation.Controllers;

import com.example.lightnovelrecommendation.Repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class GenreController {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CacheManager cacheManager;

    private final int booksPerPage = 10;

    @GetMapping("/genre")
    public String genre(Model model,
                       @RequestParam(name = "genre", defaultValue = "Action") String selectedGenre,
                       @RequestParam(name = "page", defaultValue = "1") int page) {
        List<Object[]> bookData = bookRepository.findAllBooksByGenre(selectedGenre);

        int numPages = (int) Math.ceil((double) bookData.size() / booksPerPage);
        int startIndex = (page - 1) * booksPerPage;
        int endIndex = Math.min(startIndex + booksPerPage, bookData.size());
        List<Object[]> books = bookData.subList(startIndex, endIndex);

        model.addAttribute("bookData", books);
        model.addAttribute("numPages", numPages);
        model.addAttribute("currentPage", page);
        model.addAttribute("selectedGenre", selectedGenre);

        return "genre";
    }


}


