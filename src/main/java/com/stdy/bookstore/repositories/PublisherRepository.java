package com.stdy.bookstore.repositories;

import com.stdy.bookstore.data.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PublisherRepository extends JpaRepository<Publisher, Long> {
}
