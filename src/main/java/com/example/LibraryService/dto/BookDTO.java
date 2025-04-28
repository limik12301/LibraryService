package com.example.LibraryService.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Value;

@Getter
@Setter
@NoArgsConstructor
public class BookDTO {

    @NotEmpty(message = "id should not be empty")
    int id;

    @NotEmpty(message = "title should not be empty")
    @Size(min = 2, max = 30, message = "title should be between 2 and 30")
    private String title;

    @NotEmpty(message = "author should not be empty")
    @Size(min = 2, max = 30, message = "author should be between 2 and 30")
    private String author;

    @Min(value = -1, message = "copies should be greater then -1")
    private int copies;
}
