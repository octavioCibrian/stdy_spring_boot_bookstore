package com.stdy.bookstore.controller;

import com.stdy.bookstore.data.Publisher;
import com.stdy.bookstore.repositories.PublisherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/publishers")
@RequiredArgsConstructor
public class PublisherController {

    private final PublisherRepository publisherRepository;

    @PostMapping
    public ResponseEntity<Publisher> savePublisher(@RequestBody Publisher publisher) {
        Publisher savedPublisher = publisherRepository.save(publisher);
        return ResponseEntity.ok(savedPublisher);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Publisher> getPublisher(@PathVariable Long id) {
        Optional<Publisher> publisher = publisherRepository.findById(id);
        return publisher.map(ResponseEntity::ok).orElseGet( () -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Publisher>> getAllPublishers() {
        List<Publisher> publishers = publisherRepository.findAll();
        return ResponseEntity.ok(publishers);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Publisher> updatePublisher(@PathVariable Long id, @RequestBody Publisher publisher) {
        return publisherRepository.findById(id)
                .map(existingPublisher -> {
                    existingPublisher.setName(publisher.getName());
                    existingPublisher.setAddress(publisher.getAddress());
                    Publisher updatePublisher = publisherRepository.save(existingPublisher);
                    return ResponseEntity.ok(updatePublisher);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

}
