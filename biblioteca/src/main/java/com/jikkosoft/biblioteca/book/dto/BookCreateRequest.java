package com.jikkosoft.biblioteca.book.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
/**
 * clase de lo que requiere servicio para crear una libros
 */
public class BookCreateRequest {
    private Long libraryId;
    private String title;
    private String author;
    private String genre;
}
