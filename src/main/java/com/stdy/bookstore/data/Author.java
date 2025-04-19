package com.stdy.bookstore.data;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "authors")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Author {
    @Id @GeneratedValue
    private Long id;
    private String name;
    private String biography;
    @ManyToOne
    private Publisher publisher;
}
