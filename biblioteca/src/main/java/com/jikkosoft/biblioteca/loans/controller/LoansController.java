package com.jikkosoft.biblioteca.loans.controller;

import com.jikkosoft.biblioteca.loans.dto.LoansCreateRequest;
import com.jikkosoft.biblioteca.loans.dto.LoansResponse;
import com.jikkosoft.biblioteca.loans.dto.LoansUpdateRequest;
import com.jikkosoft.biblioteca.loans.services.LoansService;
import com.jikkosoft.biblioteca.response.ErrorResponse;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/loan")
public class LoansController {

    private final LoansService loansService;

    @Autowired
    public LoansController(LoansService loansService) {
        this.loansService = loansService;
    }

    @PostMapping(path = "/save")
    public LoansResponse createLoans(@RequestParam LoansCreateRequest loansCreateRequest) {
        return this.loansService.createLoan(loansCreateRequest);
    }

    @PutMapping(path = "/update")
    public LoansResponse updateLoans(@RequestParam LoansUpdateRequest loansUpdateRequest) {
        return this.loansService.updateLoan(loansUpdateRequest);
    }
    @DeleteMapping(path = "/delete")
    public void deleteLoans(@RequestParam(name = "id") Long id){
        this.loansService.deleteLoan(id);
    }
    @GetMapping(path = "/all")
    public List<LoansResponse> findAllLoans() {
        return this.loansService.findAll();
    }
    @GetMapping(path = "/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        try {
            LoansResponse LoansResponse = this.loansService.findById(id);
            return new ResponseEntity<>(LoansResponse, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(new ErrorResponse(e.getMessage()), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorResponse("Error interno del servidor"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
