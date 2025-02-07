package com.stdy.bookstore.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name ="publishers")
@Data
public class Publisher {
    @Id @GeneratedValue
    private Long Id;
    private String name;
    private String address;
}
