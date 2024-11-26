package com.jikkosoft.biblioteca.book.controller;

import com.jikkosoft.biblioteca.book.dto.BookCreateRequest;
import com.jikkosoft.biblioteca.book.dto.BookResponse;
import com.jikkosoft.biblioteca.book.dto.BookUpdateRequest;
import com.jikkosoft.biblioteca.response.ErrorResponse;
import com.jikkosoft.biblioteca.book.services.BookService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/book")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/save")
    public BookResponse createBook(@RequestParam BookCreateRequest bookCreateRequest) {
        return this.bookService.createBook(bookCreateRequest);
    }

    @PutMapping("/update")
    public BookResponse updateBook(@RequestParam BookUpdateRequest bookUpdateRequest) {
        return this.bookService.updateBook(bookUpdateRequest);
    }

    @DeleteMapping(path = "/delete")
    public void deleteBook (@RequestParam(name = "id") Long id){
        this.bookService.deleteBook(id);
    }

    @GetMapping(path = "/all")
    public List<BookResponse> findAllBooks(){
        return this.bookService.findAll();
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        try {
            BookResponse bookResponse = this.bookService.findById(id);
            return new ResponseEntity<>(bookResponse, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(new ErrorResponse(e.getMessage()), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorResponse("Error interno del servidor"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
