package org.library.controller;

import org.library.model.Book;
import org.library.repository.BookRepository;

import java.util.List;

public class BookController {

    private final BookRepository bookRepository;

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void saveBookController(Book book) {
        bookRepository.saveBook(book);
    }

    public List<Book> findAllController() {
        return bookRepository.findAll();
    }

    public void updateBookController(int id, Book book) {
        bookRepository.update(id, book);
    }

    public void deleteBookController(int id, Book book) {
        bookRepository.delete(id, book);
    }
}