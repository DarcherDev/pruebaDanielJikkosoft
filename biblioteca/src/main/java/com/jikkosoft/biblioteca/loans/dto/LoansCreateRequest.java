package com.jikkosoft.biblioteca.loans.dto;

import lombok.Builder;
import lombok.Data;

import java.sql.Date;

@Data
@Builder
/**
 * clase de lo que requiere servicio para crear un prestamos
 */
public class LoansCreateRequest {
    private Long bookId;
    private Long membersId;
    private Date loanDate;
    private Date returnDate;
    private boolean returned;
    private Date created_at;
}
