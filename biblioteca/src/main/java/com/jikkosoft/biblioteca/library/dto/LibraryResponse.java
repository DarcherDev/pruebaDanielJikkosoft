package com.jikkosoft.biblioteca.library.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
/**
 * clase de lo que devuelve el servicio de libreria
 */
public class LibraryResponse {

    private Long id;
    private String name;
    private String location;
}
