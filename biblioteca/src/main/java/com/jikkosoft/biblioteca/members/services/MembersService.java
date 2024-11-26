package com.jikkosoft.biblioteca.members.services;

import com.jikkosoft.biblioteca.members.dto.MembersCreateRequest;
import com.jikkosoft.biblioteca.members.dto.MembersResponse;
import com.jikkosoft.biblioteca.members.dto.MembersUpdateRequest;
import com.jikkosoft.biblioteca.members.model.MembersModel;
import com.jikkosoft.biblioteca.members.repositories.MembersRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MembersService {

    private final MembersRepository membersRepository;
    @Autowired
    public MembersService(MembersRepository membersRepository) {
        this.membersRepository = membersRepository;
    }

    @Transactional
    public MembersResponse createMembers(MembersCreateRequest membersCreateRequest) {
        if (membersRepository.existsByEmail(membersCreateRequest.getEmail())) {
            throw new IllegalArgumentException("El correo ya está registrado: " + membersCreateRequest.getEmail());
        }
        MembersModel members = buildMembersCreateRequest(membersCreateRequest);
        this.membersRepository.save(members);

        return MembersResponse.builder()
                .firstName(members.getFirstName())
                .lastName(members.getLastName())
                .email(members.getEmail())
                .phone(members.getPhone())
                .build();
    }
    @Transactional
    public MembersResponse updateMembers(MembersUpdateRequest membersUpdateRequest) {
        if (membersRepository.existsByEmail(membersUpdateRequest.getEmail())) {
            throw new IllegalArgumentException("El correo ya está registrado: " + membersUpdateRequest.getEmail());
        }
        MembersModel members = buildMembersUpdateRequest(membersUpdateRequest);
        this.membersRepository.save(members);

        return MembersResponse.builder()
                .firstName(members.getFirstName())
                .lastName(members.getLastName())
                .email(members.getEmail())
                .phone(members.getPhone())
                .build();
    }

    @Transactional
    public void deleteMembers(Long id){
        this.membersRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<MembersResponse> findAll(){
        return this.membersRepository.findAll().stream()
                .map(members -> MembersResponse.builder()
                        .id(members.getId())
                        .firstName(members.getFirstName())
                        .lastName(members.getLastName())
                        .email(members.getEmail())
                        .phone(members.getPhone())
                        .build())
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public MembersResponse findById(Long id){
        MembersModel members = this.membersRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Members not found"));
        return  MembersResponse.builder()
                .firstName(members.getFirstName())
                .lastName(members.getLastName())
                .email(members.getEmail())
                .phone(members.getPhone())
                .build();
    }

    /**
     * metodo para crear un modelo de la miembro basado en la info del request
     * @param membersCreateRequest
     * @return
     */
    private MembersModel buildMembersCreateRequest(MembersCreateRequest membersCreateRequest) {
        return MembersModel.builder()
                .firstName(membersCreateRequest.getFirstName())
                .lastName(membersCreateRequest.getLastName())
                .email(membersCreateRequest.getEmail())
                .phone(membersCreateRequest.getPhone())
                .created_at(new Date(System.currentTimeMillis()))
                .build();
    }

    /**
     * metodo para actualizar un modelo de la miembros basado en la info del request
     * @param membersUpdateRequest
     * @return
     */
    private MembersModel buildMembersUpdateRequest(MembersUpdateRequest membersUpdateRequest) {
        return MembersModel.builder()
                .id(membersUpdateRequest.getId())
                .firstName(membersUpdateRequest.getFirstName())
                .lastName(membersUpdateRequest.getLastName())
                .email(membersUpdateRequest.getEmail())
                .phone(membersUpdateRequest.getPhone())
                .created_at(new Date(System.currentTimeMillis()))
                .build();
    }
}
