package com.jikkosoft.biblioteca.library.services;

import com.jikkosoft.biblioteca.library.dto.LibraryCreateRequest;
import com.jikkosoft.biblioteca.library.dto.LibraryResponse;
import com.jikkosoft.biblioteca.library.dto.LibraryUpdateRequest;
import com.jikkosoft.biblioteca.library.model.LibraryModel;
import com.jikkosoft.biblioteca.library.repositories.LibraryRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LibraryService {

    private final LibraryRepository libraryRepository;

    @Autowired
    public LibraryService(LibraryRepository libraryRepository) {
        this.libraryRepository = libraryRepository;
    }

    @Transactional
    public LibraryResponse creatLibrary(LibraryCreateRequest libraryCreateRequest) {

        LibraryModel library = buildLibraryCreateRequest(libraryCreateRequest);
        this.libraryRepository.save(library);

        return LibraryResponse.builder()
                .id(library.getId())
                .name(library.getName())
                .location(library.getLocation())
                .build();
    }

    @Transactional
    public LibraryResponse updateLibrary(LibraryUpdateRequest libraryUpdateRequest) {

        LibraryModel library = buildLibraryUpdateRequest(libraryUpdateRequest);
        this.libraryRepository.save(library);

        return LibraryResponse.builder()
                .id(library.getId())
                .name(library.getName())
                .location(library.getLocation())
                .build();
    }

    @Transactional
    public void deleteLibrary(Long id) {
        if(!this.libraryRepository.existsById(id)) {
            throw new EntityNotFoundException("Registro con ID " + id + " no encontrado");
        }
        this.libraryRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<LibraryResponse> findAll() {
        return this.libraryRepository.findAll().stream()
                .map(library -> LibraryResponse.builder()
                        .id(library.getId())
                        .name(library.getName())
                        .location(library.getLocation())
                        .build())
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public LibraryResponse findById(Long id) {
        LibraryModel library = this.libraryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Libreria no encontrada"));

        return LibraryResponse.builder()
                .id(library.getId())
                .name(library.getName())
                .location(library.getLocation())
                .build();
    }

    /**
     * metodo para crear un modelo de la libreria basado en la info del request
     * @param libraryCreateRequest
     * @return
     */
    private LibraryModel buildLibraryCreateRequest(LibraryCreateRequest libraryCreateRequest) {
        return LibraryModel.builder()
                .name(libraryCreateRequest.getName())
                .location(libraryCreateRequest.getLocation())
                .created_at(new Date(System.currentTimeMillis()))
                .build();
    }

    /**
     * metodo para actualizar un modelo de la libreria basado en la info del request
     * @param libraryUpdateRequest
     * @return
     */
    private LibraryModel buildLibraryUpdateRequest(LibraryUpdateRequest libraryUpdateRequest) {
        return  LibraryModel.builder()
                .id(libraryUpdateRequest.getId())
                .name(libraryUpdateRequest.getName())
                .location(libraryUpdateRequest.getLocation())
                .created_at(new Date(System.currentTimeMillis()))
                .build();
    }
}

