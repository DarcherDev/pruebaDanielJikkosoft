package com.jikkosoft.biblioteca.book.model;

import com.jikkosoft.biblioteca.library.model.LibraryModel;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.sql.Date;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "books")
public class BookModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bo_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "bo_library_id", referencedColumnName = "li_id", nullable = false)
    private LibraryModel libraryModel;

    @NotNull
    @Column(name="bo_title")
    private String title;

    @NotNull
    @Column(name="bo_author")
    private String author;

    @Column(name="bo_genre")
    private String genre;

    @Column(name="bo_is_available")
    private boolean isAvailable;

    @Column(name="bo_created_at")
    private Date created_at;

}

