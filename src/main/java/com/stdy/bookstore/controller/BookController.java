package com.stdy.bookstore.controller;


import com.stdy.bookstore.data.Book;
import com.stdy.bookstore.repositories.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {

    private final BookRepository bookRepository;

    @PostMapping
    @CacheEvict(value = "books", allEntries = true)
    public ResponseEntity<Book> saveBook(@RequestBody Book book) {
        Book savedBook = bookRepository.save(book);
        return ResponseEntity.ok(savedBook);
    }

    @GetMapping("/{id}")
    @Cacheable("books")
    public ResponseEntity<Book> getBook(@PathVariable Long id) {
        Optional<Book> book = bookRepository.findById(id);
        return book.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    @Cacheable("books")
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @PutMapping("/{id}")
    @CacheEvict(value = "books", allEntries = true)
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book book) {
        return bookRepository.findById(id)
                .map(existingBook -> {
                    existingBook.setIsbn(book.getIsbn());
                    existingBook.setTitle(book.getTitle());
                    existingBook.setAuthors(book.getAuthors());
                    Book updatedBook = bookRepository.save(existingBook);
                    return ResponseEntity.ok(updatedBook);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Transactional
    @DeleteMapping("/{id}")
    @CacheEvict(value = "books", key = "#id")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        if (bookRepository.existsById(id)) {
            bookRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
