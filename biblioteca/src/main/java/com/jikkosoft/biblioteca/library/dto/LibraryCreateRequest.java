package com.jikkosoft.biblioteca.library.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
/**
 * clase de lo que requiere servicio para crear una libreria
 */
public class LibraryCreateRequest {
    private String name;
    private String location;
}
