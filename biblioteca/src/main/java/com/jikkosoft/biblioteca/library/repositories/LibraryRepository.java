package com.jikkosoft.biblioteca.library.repositories;

import com.jikkosoft.biblioteca.library.model.LibraryModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LibraryRepository extends JpaRepository<LibraryModel, Long> {
}
