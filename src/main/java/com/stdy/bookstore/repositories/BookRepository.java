package com.stdy.bookstore.repositories;

import com.stdy.bookstore.data.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
