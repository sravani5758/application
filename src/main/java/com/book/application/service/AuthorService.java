package com.book.application.service;


import com.book.application.entity.Author;
import com.book.application.repo.AuthorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepo authorRepo;

    public Author createAuthor(Author author){
        return authorRepo.save(author);
    }

    public List<Author> getAllAuthors() {
        return authorRepo.findAll();
    }

    public Author getById(Long id) {
        Author author = authorRepo.findById(id).get();
        return author;


    }
    public Author updateAuthor(Long id, Author updatedAuthor) {
        return authorRepo.findById(id)
                .map(existingAuthor -> {
                    existingAuthor.setName(updatedAuthor.getName());
                     // Update other fields
                    return authorRepo.save(existingAuthor);
                })
                .orElseThrow(() -> new RuntimeException("Author not found with ID: " + id));
    }

    public String deleteAuthor(Long id) {
        Author author = authorRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Author not found with ID: " + id));
        authorRepo.delete(author);
        return "Author with ID " + id + " deleted successfully.";
    }
}
