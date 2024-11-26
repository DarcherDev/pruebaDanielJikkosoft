package com.jikkosoft.biblioteca.loans.model;

import com.jikkosoft.biblioteca.book.model.BookModel;
import com.jikkosoft.biblioteca.members.model.MembersModel;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.sql.Date;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "loans")
public class LoansModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lo_id")
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "lo_book_id", referencedColumnName = "bo_id", nullable = false)
    private BookModel bookModel;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "lo_member_id", referencedColumnName = "me_id", nullable = false)
    private MembersModel membersModel;

    @NotNull
    @Column(name="lo_loan_date")
    private Date loanDate;

    @Column(name="lo_return_date")
    private Date returnDate;

    @Column(name="lo_returned")
    private boolean returned;

    @Column(name="lo_created_at")
    private Date created_at;

}
