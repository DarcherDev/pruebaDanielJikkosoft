package com.jikkosoft.biblioteca.library.controller;

import com.jikkosoft.biblioteca.library.dto.LibraryCreateRequest;
import com.jikkosoft.biblioteca.library.dto.LibraryResponse;
import com.jikkosoft.biblioteca.library.dto.LibraryUpdateRequest;
import com.jikkosoft.biblioteca.response.ErrorResponse;
import com.jikkosoft.biblioteca.library.services.LibraryService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/library")
public class LibraryController {

    private LibraryService libraryService;

    @Autowired
    public LibraryController(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    @PostMapping("/save")
    public LibraryResponse createLibrary(@RequestBody LibraryCreateRequest libraryCreateRequest) {
        return this.libraryService.creatLibrary(libraryCreateRequest);
    }

    @PutMapping("/update")
    public LibraryResponse updateLibrary(@RequestBody LibraryUpdateRequest libraryUpdateRequest) {
        return this.libraryService.updateLibrary(libraryUpdateRequest);
    }

    @DeleteMapping(path = "/delete")
    public void deleteLibrary (@RequestParam(name = "id") Long id){
        this.libraryService.deleteLibrary(id);
    }

    @GetMapping(path = "/all")
    public List<LibraryResponse> findAll(){
        return this.libraryService.findAll();
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        try {
            LibraryResponse response = this.libraryService.findById(id);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(new ErrorResponse(e.getMessage()), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorResponse("Error interno del servidor"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



}
