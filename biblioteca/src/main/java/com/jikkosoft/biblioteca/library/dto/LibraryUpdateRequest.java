package com.jikkosoft.biblioteca.library.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
/**
 * clase de lo que requiere servicio para actualizar una libreria
 */
public class LibraryUpdateRequest {
    private Long id;
    private String name;
    private String location;
}
