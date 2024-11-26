package com.jikkosoft.biblioteca.members.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.sql.Date;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "members")
public class MembersModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "me_id")
    private Long id;

    @NotNull
    @Size(max=100)
    @Column(name="me_first_name")
    private String firstName;

    @NotNull
    @Size(max=100)
    @Column(name="me_last_name")
    private String lastName;

    @NotNull
    @Column(name="me_email")
    private String email;

    @Size(max=15)
    @Column(name="me_phone")
    private String phone;

    @Column(name="me_created_at")
    private Date created_at;

}
