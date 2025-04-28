package com.example.LibraryService.util;

import com.example.LibraryService.dto.BookDTO;
import com.example.LibraryService.entity.Book;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BooksMapper {
    Book convertToBook(BookDTO book);
    BookDTO convertToBookDTO(Book book);
}
