package com.stdy.bookstore.controller;

import com.stdy.bookstore.data.Review;
import com.stdy.bookstore.repositories.ReviewRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
@AllArgsConstructor
public class ReviewController {

    ReviewRepository reviewRepository;

    @PostMapping
    public ResponseEntity<Review> saveReview(@RequestBody Review review) {
        return ResponseEntity.ok(reviewRepository.save(review));

    }

    @GetMapping
    public ResponseEntity<List<Review>> getAll() {
        return ResponseEntity.ok(reviewRepository.findAll());
    }

}
