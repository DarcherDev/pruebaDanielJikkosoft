package com.jikkosoft.biblioteca.members.repositories;

import com.jikkosoft.biblioteca.members.model.MembersModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MembersRepository extends JpaRepository<MembersModel, Long> {
    boolean existsByEmail(String email);
}
