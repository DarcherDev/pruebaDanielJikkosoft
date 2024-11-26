package com.jikkosoft.biblioteca.members.controller;

import com.jikkosoft.biblioteca.members.dto.MembersCreateRequest;
import com.jikkosoft.biblioteca.members.dto.MembersResponse;
import com.jikkosoft.biblioteca.members.dto.MembersUpdateRequest;
import com.jikkosoft.biblioteca.members.services.MembersService;
import com.jikkosoft.biblioteca.response.ErrorResponse;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/members")
public class MembersController {

    private final MembersService membersService;
    @Autowired
    public MembersController(MembersService membersService) {
        this.membersService = membersService;
    }

    @PostMapping(path = "/save")
    public ResponseEntity<?> createMembers(@RequestBody MembersCreateRequest membersCreateRequest) {
        try {
            MembersResponse response = this.membersService.createMembers(membersCreateRequest);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ErrorResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorResponse("Error interno del servidor"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(path ="/update")
    public ResponseEntity<?> updateMembers(@RequestBody MembersUpdateRequest membersUpdateRequest) {
        try {
            MembersResponse response= this.membersService.updateMembers(membersUpdateRequest);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ErrorResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorResponse("Error interno del servidor"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(path ="/delete")
    public void deleteMembers (@RequestParam (name = "id") Long id) {
        this.membersService.deleteMembers(id);
    }

    @GetMapping(path = "/all")
    public List<MembersResponse> findAll() {
        return this.membersService.findAll();
    }

    @GetMapping(path="/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        try {
            MembersResponse response = this.membersService.findById(id);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(new ErrorResponse(e.getMessage()), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorResponse("Error interno del servidor"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
