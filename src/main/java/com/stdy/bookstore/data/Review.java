package com.stdy.bookstore.data;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.annotation.Collation;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "reviews")
@Data
public class Review {
    @Id
    private String id;
    private Long bookId;
    private String reviewerName;
    private String comment;
    private int rating;
}
