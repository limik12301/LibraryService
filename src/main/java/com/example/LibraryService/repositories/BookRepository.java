package com.example.LibraryService.repositories;

import com.example.LibraryService.exception.BookNotFoundException;
import com.example.LibraryService.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    default Book findBookById(int id){
        return findById(id).orElseThrow(BookNotFoundException::new);
    };
}
