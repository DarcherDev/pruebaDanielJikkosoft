package com.jikkosoft.biblioteca.book.services;

import com.jikkosoft.biblioteca.book.dto.BookCreateRequest;
import com.jikkosoft.biblioteca.book.dto.BookResponse;
import com.jikkosoft.biblioteca.book.dto.BookUpdateRequest;
import com.jikkosoft.biblioteca.book.model.BookModel;
import com.jikkosoft.biblioteca.library.model.LibraryModel;
import com.jikkosoft.biblioteca.book.repositories.BookRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Transactional
    public BookResponse createBook(BookCreateRequest bookCreateRequest) {
        BookModel book = buildBookCreateRequest(bookCreateRequest);
        this.bookRepository.save(book);

        return BookResponse.builder()
                .id(book.getId())
                .libraryId(book.getLibraryModel().getId())
                .title(book.getTitle())
                .author(book.getAuthor())
                .genre(book.getGenre())
                .isAvailable(book.isAvailable())
                .build();
    }
    @Transactional
    public BookResponse updateBook(BookUpdateRequest bookUpdateRequest) {

        BookModel book = buildBookUpdateRequestBookModel(bookUpdateRequest);
        this.bookRepository.save(book);

        return BookResponse.builder()
                .id(book.getId())
                .libraryId(book.getLibraryModel().getId())
                .title(book.getTitle())
                .author(book.getAuthor())
                .genre(book.getGenre())
                .isAvailable(book.isAvailable())
                .build();
    }

    @Transactional
    public void deleteBook(Long id) {
        this.bookRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<BookResponse> findAll(){
        return this.bookRepository.findAll().stream()
                .map(book -> BookResponse.builder()
                        .id(book.getId())
                        .libraryId(book.getLibraryModel().getId())
                        .title(book.getTitle())
                        .author(book.getAuthor())
                        .genre(book.getGenre())
                        .isAvailable(book.isAvailable())
                        .build())
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public BookResponse findById(Long id) {
        BookModel book = this.bookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("libro no encontrado"));
        return BookResponse.builder()
                .id(book.getId())
                .libraryId(book.getLibraryModel().getId())
                .title(book.getTitle())
                .author(book.getAuthor())
                .genre(book.getGenre())
                .isAvailable(book.isAvailable())
                .build();
    }

    /**
     * metodo para crear un modelo del libro basado en la info del request
     * @param bookCreateRequest
     * @return
     */
    private BookModel buildBookCreateRequest (BookCreateRequest bookCreateRequest) {
        return BookModel.builder()
                .libraryModel(LibraryModel.builder().id(bookCreateRequest.getLibraryId()).build())
                .title(bookCreateRequest.getTitle())
                .author(bookCreateRequest.getAuthor())
                .genre(bookCreateRequest.getGenre())
                .isAvailable(true)
                .created_at(new Date(System.currentTimeMillis()))
                .build();
    }

    /**
     * metodo para actualizar un modelo de libro basado en la info del request
     * @param bookUpdateRequest
     * @return
     */
    private BookModel buildBookUpdateRequestBookModel(BookUpdateRequest bookUpdateRequest) {
        return BookModel.builder()
                .id(bookUpdateRequest.getId())
                .libraryModel(LibraryModel.builder().id(bookUpdateRequest.getLibraryId()).build())
                .title(bookUpdateRequest.getTitle())
                .author(bookUpdateRequest.getAuthor())
                .genre(bookUpdateRequest.getGenre())
                .isAvailable(bookUpdateRequest.isAvailable())
                .created_at(new Date(System.currentTimeMillis()))
                .build();
    }
}
