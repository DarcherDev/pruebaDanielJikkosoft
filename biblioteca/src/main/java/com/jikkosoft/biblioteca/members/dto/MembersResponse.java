package com.jikkosoft.biblioteca.members.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
/**
 * clase de lo que devuelve el servicio de miembros
 */
public class MembersResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;

}
