package com.example.enterBook.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter @Getter
@Table(name="books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "author", nullable = false)
    private String author;

    @Enumerated(EnumType.STRING)
    @Column(name = "genre", nullable = false)
    private Genre genre;

    @Column(name = "publisher", nullable = false)
    private String publisher;

    @Column(name = "price", nullable = false, precision = 5, scale = 2)
    private BigDecimal price;

    @Column(name = "publish_date", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date publishDate;
}
