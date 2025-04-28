package com.example.LibraryService.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "title")
    @NotEmpty(message = "title should not be empty")
    @Size(min = 2, max = 30, message = "title should be between 2 and 30")
    private String title;

    @Column(name = "author")
    @NotEmpty(message = "author should not be empty")
    @Size(min = 2, max = 30, message = "author should be between 2 and 30")
    private String author;


    @Column(name = "copies")
    @Min(value = -1, message = "copies should be greater then -1")
    private int copies;

}
