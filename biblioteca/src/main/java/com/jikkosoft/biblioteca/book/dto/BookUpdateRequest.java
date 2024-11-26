package com.jikkosoft.biblioteca.book.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
/**
 * clase de lo que requiere servicio para actualizar un libro
 */
public class BookUpdateRequest {
    private Long id;
    private Long libraryId;
    private String title;
    private String author;
    private String genre;
    private boolean isAvailable;
}
