package com.jikkosoft.biblioteca.loans.services;

import com.jikkosoft.biblioteca.book.model.BookModel;
import com.jikkosoft.biblioteca.loans.dto.LoansCreateRequest;
import com.jikkosoft.biblioteca.loans.dto.LoansResponse;
import com.jikkosoft.biblioteca.loans.dto.LoansUpdateRequest;
import com.jikkosoft.biblioteca.loans.model.LoansModel;
import com.jikkosoft.biblioteca.loans.repositories.LoansRepository;
import com.jikkosoft.biblioteca.members.model.MembersModel;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LoansService {

    private LoansRepository loansRepository;
    @Autowired
    public LoansService(LoansRepository loansRepository) {
        this.loansRepository = loansRepository;
    }

    @Transactional
    public LoansResponse createLoan(LoansCreateRequest loansCreateRequest) {
        LoansModel loans = buildLoanCreateRequest(loansCreateRequest);
        loansRepository.save(loans);

        return LoansResponse.builder()
                .id(loans.getId())
                .bookId(loans.getBookModel().getId())
                .membersId(loans.getMembersModel().getId())
                .loanDate(loans.getLoanDate())
                .returnDate(loans.getReturnDate())
                .returned(loans.isReturned())
                .build();
    }

    @Transactional
    public LoansResponse updateLoan(LoansUpdateRequest loansUpdateRequest) {
        LoansModel loans = buildLoanUpdateRequest(loansUpdateRequest);
        loansRepository.save(loans);

        return LoansResponse.builder()
                .id(loans.getId())
                .bookId(loans.getBookModel().getId())
                .membersId(loans.getMembersModel().getId())
                .loanDate(loans.getLoanDate())
                .returnDate(loans.getReturnDate())
                .returned(loans.isReturned())
                .build();
    }

    @Transactional
    public void deleteLoan(Long id) {
        loansRepository.deleteById(id);
    }
    @Transactional(readOnly = true)
    public List<LoansResponse> findAll(){
        return this.loansRepository.findAll().stream()
                .map(loans -> LoansResponse.builder()
                        .id(loans.getId())
                        .bookId(loans.getBookModel().getId())
                        .membersId(loans.getMembersModel().getId())
                        .loanDate(loans.getLoanDate())
                        .returnDate(loans.getReturnDate())
                        .returned(loans.isReturned())
                        .build())
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public LoansResponse findById(Long id) {
        LoansModel loans = this.loansRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("libro no encontrado"));
        return LoansResponse.builder()
                .id(loans.getId())
                .bookId(loans.getBookModel().getId())
                .membersId(loans.getMembersModel().getId())
                .loanDate(loans.getLoanDate())
                .returnDate(loans.getReturnDate())
                .returned(loans.isReturned())
                .build();
    }

    /**
     * metodo para crear un modelo del prestamo basado en el request
     * @param loansCreateRequest
     * @return
     */
    private LoansModel buildLoanCreateRequest(LoansCreateRequest loansCreateRequest) {
        return LoansModel.builder()
                .bookModel(BookModel.builder().id(loansCreateRequest.getBookId()).build())
                .membersModel(MembersModel.builder().id(loansCreateRequest.getMembersId()).build())
                .loanDate(loansCreateRequest.getLoanDate())
                .returnDate(loansCreateRequest.getReturnDate())
                .returned(loansCreateRequest.isReturned())
                .created_at(new Date(System.currentTimeMillis()))
                .build();
    }

    /**
     * metodo para actualizar un modelo de prestamo basado en el request
     * @param loansUpdateRequest
     * @return
     */
    private LoansModel buildLoanUpdateRequest(LoansUpdateRequest loansUpdateRequest) {
        return LoansModel.builder()
                .id(loansUpdateRequest.getId())
                .bookModel(BookModel.builder().id(loansUpdateRequest.getBookId()).build())
                .membersModel(MembersModel.builder().id(loansUpdateRequest.getMemberId()).build())
                .loanDate(loansUpdateRequest.getLoanDate())
                .returnDate(loansUpdateRequest.getReturnDate())
                .returned(loansUpdateRequest.isReturned())
                .created_at(new Date(System.currentTimeMillis()))
                .build();
    }

}
