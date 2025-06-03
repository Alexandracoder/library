package org.library;

import org.library.controller.BookController;
import org.library.repository.BookRepository;
import org.library.view.BookView;

public class Main {
    public static void main(String[] args) {
        BookRepository bookRepository = new BookRepository();
        BookController bookController = new BookController(bookRepository);
        BookView bookView = new BookView(bookController);
        bookView.saveBookView();
        bookView.showBooks();
        bookView.update();
        bookView.delete();
    }
}