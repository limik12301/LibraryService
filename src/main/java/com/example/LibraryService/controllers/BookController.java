package com.example.LibraryService.controllers;

import com.example.LibraryService.dto.BookDTO;
import com.example.LibraryService.services.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping("/{id}")
    public BookDTO getBookById(@PathVariable int id){
        return bookService.getBookById(id);
    }

}
