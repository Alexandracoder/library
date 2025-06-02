package org.library.view;

import org.library.controller.BookController;
import org.library.model.Book;

import java.util.List;
import java.util.Scanner;

public class BookView {
    private final BookController bookController;

    public BookView(BookController bookController) {
        this.bookController = bookController;
    }

    public void saveBookView() {
        Book book = generateBook();

        bookController.saveBookController(book);
    }

    public Book generateBook() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please enter the title of the book: ");
        String title = scanner.nextLine();

        System.out.println("Please enter the name(s) of the author(s): ");
        String authors = scanner.nextLine();

        System.out.println("Please enter the description: ");
        String description = scanner.nextLine();

        System.out.println("Please enter the ISBN: ");
        String isbn = scanner.nextLine();

        System.out.println("Please enter the genre: ");
        String genre = scanner.nextLine();

        System.out.println("Please enter the year of publication: ");
        int year = scanner.nextInt();

        Book book = new Book(title, authors, description, isbn, genre, year);

        scanner.close();

        return book;
    }

    public void showBooks() {
        List<Book> bookList = bookController.findAllController();

        for (Book book : bookList) {
            System.out.println("Title: " + book.getTitle() + " Author(s): " + book.getAuthors() + " Description: " + book.getDescription() + " ISBN: " + book.getIsbn()
                    + " Genre: " + book.getGenre() + " Year: " + book.getYear());
        }
    }
}