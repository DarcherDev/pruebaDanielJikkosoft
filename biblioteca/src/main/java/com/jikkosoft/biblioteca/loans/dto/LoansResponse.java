package com.jikkosoft.biblioteca.loans.dto;

import com.jikkosoft.biblioteca.book.model.BookModel;
import com.jikkosoft.biblioteca.members.model.MembersModel;
import lombok.Builder;
import lombok.Data;

import java.sql.Date;

@Data
@Builder
/**
 * clase de lo que devuelve el servicio de prestamo
 */
public class LoansResponse {
    private Long id;
    private Long bookId;
    private Long membersId;
    private Date loanDate;
    private Date returnDate;
    private boolean returned;
}




