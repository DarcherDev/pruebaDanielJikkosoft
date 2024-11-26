package com.jikkosoft.biblioteca.book.repositories;

import com.jikkosoft.biblioteca.book.model.BookModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<BookModel, Long> {
}
