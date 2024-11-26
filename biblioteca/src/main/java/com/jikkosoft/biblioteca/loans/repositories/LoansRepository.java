package com.jikkosoft.biblioteca.loans.repositories;

import com.jikkosoft.biblioteca.loans.model.LoansModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoansRepository extends JpaRepository<LoansModel, Long> {
}
