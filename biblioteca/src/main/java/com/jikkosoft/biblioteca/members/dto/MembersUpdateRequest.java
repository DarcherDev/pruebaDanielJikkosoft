package com.jikkosoft.biblioteca.members.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
/**
 * clase de lo que requiere servicio para actualizar un miembro
 */
public class MembersUpdateRequest {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
}
