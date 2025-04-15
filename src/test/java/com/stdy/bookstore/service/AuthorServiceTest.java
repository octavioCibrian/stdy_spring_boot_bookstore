package com.stdy.bookstore.service;

import com.stdy.bookstore.data.Author;
import com.stdy.bookstore.data.Publisher;
import com.stdy.bookstore.repositories.AuthorRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AuthorServiceTest {

    @Mock
    private AuthorRepository authorRepository;

    @InjectMocks
    private AuthorService authorService;

    private Author givenAuthor;
    private Author savedAuthor;
    @BeforeEach
    void setup() {
        Publisher defaultPublisher = Publisher.builder().name("Pack Publishing").build();
        savedAuthor = Author.builder()
                .id(1L)
                .name("Author Name")
                .publisher(defaultPublisher)
                .biography("Biography of Author")
                .build();

        givenAuthor = Author.builder()
                .name("Author Name")
                .biography("Biography of Author")
                .publisher(defaultPublisher)
                .build();
    }

    @Test
    void givenExistingAuthroId_whenGetAuthor_thenReturnAuthor() {

        when(authorRepository.findById(1L)).thenReturn(Optional.of(savedAuthor));

        Optional<Author> author = authorService.getAuthor(1L);

        assertTrue(author.isPresent(), "Author should be found");
        assertEquals(1L, author.get().getId(), "Author ID should match");
    }


    @Test
    void whenCreateAuthor_thenReturnSavedAuthor() {
        when(authorRepository.save(any(Author.class))).thenReturn(savedAuthor);

        Author author = authorService.saveAuthor(givenAuthor);

        assertAll(
                () -> assertNotNull(author),
                () -> assertEquals(savedAuthor.getId(), author.getId()),
                () -> assertEquals(savedAuthor.getName(), author.getName()),
                () -> assertEquals(savedAuthor.getBiography(), author.getBiography()),
                () -> assertEquals(savedAuthor.getPublisher(), author.getPublisher())
        );
    }

    @Test
    void givenNonExistingAuthorId_whenGetAuthor_thenThrowAuthorNotFoundException() {
        when(authorRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<Author> author = authorService.getAuthor(1L);

       assertFalse(author.isPresent());
    }

    @Test
    void whenGetAllAuthors_thenReturnAllAuthors() {
        List<Author> authors = List.of(savedAuthor);

        when(authorRepository.findAll()).thenReturn(authors);

        List<Author> listAuthors = authorService.getAll();

        assertEquals(authors, listAuthors);
    }

    @Test
    void givenValidAuthor_whenUpdateAuthor_thenReturnUpdatedAuthor() {
        when(authorRepository.findById(anyLong())).thenReturn(Optional.of(givenAuthor));
        when(authorRepository.save(any(Author.class))).thenReturn(savedAuthor);
        Optional<Author> result = authorService.updateAuthor(savedAuthor.getId(), savedAuthor);

        assertTrue(result.isPresent());
        result.ifPresent(author -> {
            assertEquals("Author Name", author.getName());
            assertEquals("Biography of Author", author.getBiography());
        });

    }

    @Test
    void givenExistingAuthor_whenDeleteAuthor_thenAuthorIsDeleted() {
        when(authorRepository.findById(1L)).thenReturn(Optional.of(savedAuthor));
        authorService.deleteAuthor(1L);
        verify(authorRepository).delete(savedAuthor);
    }

    @Test
    void givenNonExistingAuthor_whenDeleteAuthor_thenThrowAuthorNotFoundException() {
        when(authorRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> authorService.deleteAuthor(1L));
    }
}