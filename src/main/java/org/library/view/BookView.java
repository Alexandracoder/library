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

    public void menu() {
        boolean condition = true;

        System.out.println("Welcome to our wonderful library!");

        do {
            Scanner scanner = new Scanner(System.in);

            System.out.println("Menu:\n\t 1.List of all books\n\t 2.Add a book\n\t 3.Update details for an existing book\n\t 4.Delete a book\n\t 5.Exit.");
            System.out.println("Choose an option: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    showBooks();
                    break;
                case 2:
                    generateBook();
                    break;
                case 3:
                    update();
                    break;
                case 4:
                    delete();
                    break;
                case 5:
                    condition = false;
                    break;
                default:
                    System.out.println("Enter a correct number");
            }
        } while (condition);
        System.out.println("Bye, see you soon!");
    }

    public void saveBookView() {
        Book book = generateBook();

        bookController.saveBookController(book);
    }

    public Book generateBook() {
        Scanner scanner = new Scanner(System.in);

        int id = 0;

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
        scanner.nextLine();

        Book book = new Book(id, title, authors, description, isbn, genre, year);

        if (proceedWithModification(scanner)) {
            System.out.println(book);
        } else {
            System.out.println("Add cancelled.");
        }
        return book;
    }

    public void showBooks() {
        List<Book> bookList = bookController.findAllController();

        for (Book book : bookList) {
            System.out.println("id: " + book.getId() + "\nTitle: " + book.getTitle() + "\nAuthor(s): " + book.getAuthors() + "\nDescription: " + book.getDescription() + "\nISBN: " + book.getIsbn()
                    + "\nGenre: " + book.getGenre() + "\nYear: " + book.getYear() + "\n");
        }
    }

    public void update() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please enter ID of the book you wish to update: ");
        int id = Integer.parseInt(scanner.nextLine());

        List<Book> allBooks = bookController.findAllController();
        Book updateBook = allBooks.stream().filter(b -> b.getId() == id).findFirst().orElse(null);

        if (updateBook == null) {
            System.out.println("Book not found.");
            return;
        }

        System.out.println("Press Enter to keep the current value.\n");

        System.out.println("Current title: " + updateBook.getTitle());
        System.out.print("New title: ");
        String title = scanner.nextLine();
        if (!title.isEmpty()) updateBook.setTitle(title);

        System.out.println("Current authors: " + updateBook.getAuthors());
        System.out.print("New authors: ");
        String authors = scanner.nextLine();
        if (!authors.isEmpty()) updateBook.setAuthors(authors);

        System.out.println("Current description: " + updateBook.getDescription());
        System.out.print("New description: ");
        String description = scanner.nextLine();
        if (!description.isEmpty()) updateBook.setDescription(description);

        System.out.println("Current ISBN: " + updateBook.getIsbn());
        System.out.print("New ISBN: ");
        String isbn = scanner.nextLine();
        if (!isbn.isEmpty()) updateBook.setIsbn(isbn);

        System.out.println("Current genre: " + updateBook.getGenre());
        System.out.print("New genre: ");
        String genre = scanner.nextLine();
        if (!genre.isEmpty()) updateBook.setGenre(genre);

        System.out.println("Current year: " + updateBook.getYear());
        System.out.print("New year: ");
        String yearInput = scanner.nextLine();
        if (!yearInput.isEmpty()) updateBook.setYear(Integer.parseInt(yearInput));


        if (proceedWithModification(scanner)) {
            bookController.updateBookController(id, updateBook);
        } else {
            System.out.println("Update cancelled.");
        }
    }

    public void delete() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please enter ID of the book you wish to delete: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        if (proceedWithModification(scanner)) {
            bookController.deleteBookController(id);
        } else {
            System.out.println("Delete cancelled.");
        }
    }

    public boolean proceedWithModification(Scanner scanner) {
        System.out.println("Do you want to proceed with this modification? (y/n): ");
        String confirmation = scanner.nextLine().toLowerCase();
        return confirmation.equals("y") || confirmation.equals("yes");
    }
}