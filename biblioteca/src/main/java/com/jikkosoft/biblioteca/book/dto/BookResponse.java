package com.jikkosoft.biblioteca.book.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
/**
 * clase de lo que devuelve el servicio de libros
 */
public class BookResponse {
    private Long id;
    private Long libraryId;
    private String title;
    private String author;
    private String genre;
    private boolean isAvailable;

}
