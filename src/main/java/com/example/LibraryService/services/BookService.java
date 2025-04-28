package com.example.LibraryService.services;


import com.example.LibraryService.dto.BookDTO;
import com.example.LibraryService.util.BooksMapper;
import com.example.LibraryService.repositories.BookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class BookService{

    private final BooksMapper booksMapper;
    private final BookRepository bookRepository;

    @Transactional
    public void saveBook(BookDTO bookDTO){
        bookRepository.save(booksMapper.convertToBook(bookDTO));
        log.info("Book: {} saved", bookDTO.getId());
    }

    @Transactional
    public void updateBook(int id, BookDTO bookDTO){
        bookRepository.findBookById(id);
        bookDTO.setId(id);
        saveBook(bookDTO);
        log.info("Book: {} updated", id);
    }

    public BookDTO getBookById(int id){
        return booksMapper.convertToBookDTO(bookRepository.findBookById(id));
    }
}
