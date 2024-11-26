package com.jikkosoft.biblioteca.loans.dto;

import lombok.Builder;
import lombok.Data;

import java.sql.Date;
@Data
@Builder
/**
 * clase de lo que requiere servicio para actualizar un prestamo
 */
public class LoansUpdateRequest {
    private Long id;
    private Long bookId;
    private Long memberId;
    private Date loanDate;
    private Date returnDate;
    private boolean returned;
    private Date created_at;
}
