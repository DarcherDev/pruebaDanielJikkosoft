package com.jikkosoft.biblioteca.members.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
/**
 * clase de lo que requiere servicio para crear un miembro
 */
public class MembersCreateRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String phone;

}
