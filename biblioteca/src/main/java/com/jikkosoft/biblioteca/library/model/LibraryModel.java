package com.jikkosoft.biblioteca.library.model;

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
@Table(name = "libraries")
public class LibraryModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "li_id")
    private Long id;

    @NotNull
    @Size(max=100)
    @Column(name="li_name")
    private String name;

    @NotNull
    @Column(name="li_location")
    private String location;


    @Column(name="li_created_at")
    private Date created_at;

}
