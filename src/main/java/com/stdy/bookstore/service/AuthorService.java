package com.stdy.bookstore.service;

import com.stdy.bookstore.data.Author;
import com.stdy.bookstore.repositories.AuthorRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthorService {


    private final AuthorRepository authorRepository;

    public Optional<Author> getAuthor(Long id) {
        return authorRepository.findById(id);
    }

    public Author saveAuthor(Author author) {
        return authorRepository.save(author);
    }

    public List<Author> getAll() {
        return authorRepository.findAll();
    }

    public Optional<Author> updateAuthor(Long id, Author author) {
        return authorRepository.findById(id)
                .map(existingAuthor -> {
                    existingAuthor.setName(author.getName());
                    existingAuthor.setBiography(author.getBiography());
                    existingAuthor.setPublisher(author.getPublisher());
                    return authorRepository.save(existingAuthor);
                });
    }

    public void deleteAuthor(Long id){
        Author author = authorRepository.findById(id)
                        .orElseThrow(() -> new EntityNotFoundException("Author not found with id: "+id));

        authorRepository.delete(author);
    }

}