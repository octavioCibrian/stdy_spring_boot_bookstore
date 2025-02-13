package com.stdy.bookstore.repositories;

import com.stdy.bookstore.data.Review;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ReviewRepository extends MongoRepository<Review, String> {
}
