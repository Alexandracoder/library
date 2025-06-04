package org.library.view;
import org.library.controller.BookController;
import org.library.model.Book;
import java.util.List;
import java.util.Scanner;

public class BookView {
    private final BookController bookController;

    private static final String RESET = "\u001B[0m";
    private static final String RED = "\u001B[31m";
    private static final String BLUE = "\u001B[36m";

    public BookView(BookController bookController) {
        this.bookController = bookController;
    }

    public void menu() {
        boolean condition = true;

        System.out.println(BLUE + "======================================");
        System.out.println("   Welcome to our wonderful Library  ");
        System.out.println( "======================================" + RESET);

        do {
            Scanner scanner = new Scanner(System.in);

            System.out.println(BLUE + "\n----------- MENU -----------" + RESET);
            System.out.println("\t1. Show all books");
            System.out.println("\t2. Add a new book");
            System.out.println("\t3. Update a book");
            System.out.println("\t4. Delete a book");
            System.out.println("\t5. Exit");
            System.out.println(BLUE + "----------------------------" + RESET);

            System.out.println("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    showBooks();
                    break;
                case 2:
                    saveBookView();
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
                    System.out.println(RED + "Invalid option. Please try again." + RESET);
            }
        } while (condition);
        System.out.println(BLUE + "Goodbye! Thanks for using our Library." + RESET);
    }

    public void saveBookView() {
        Scanner scanner = new Scanner(System.in);

        Book book = generateBook();

        if (proceedWithModification(scanner)) {
            bookController.saveBookController(book);
        } else {
            System.out.println(RED + "Book not saved." + RESET);
        }
    }

    public Book generateBook() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\nEnter Book Details");
        int id = 0;

        System.out.println("Title: ");
        String title = scanner.nextLine();

        System.out.println("Author(s): ");
        String authors = scanner.nextLine();

        System.out.println("Description (max 200 chars): ");
        String description = scanner.nextLine();

        System.out.println("ISBN: ");
        String isbn = scanner.nextLine();

        System.out.println("Genre: ");
        String genre = scanner.nextLine();

        System.out.println("Year of publication: ");
        int year = scanner.nextInt();
        scanner.nextLine();

        return new Book(id, title, authors, description, isbn, genre, year);
    }

    public void showBooks() {
        List<Book> bookList = bookController.findAllController();

        for (Book book : bookList) {
            System.out.println("----------------------------------------------------");
            System.out.println("ID: " + book.getId());
            System.out.println("Title: " + book.getTitle());
            System.out.println("Author(s): " + book.getAuthors());
            System.out.println("Description: " + book.getDescription());
            System.out.println("ISBN: " + book.getIsbn());
            System.out.println("Genre: " + book.getGenre());
            System.out.println("Year: " + book.getYear());
        System.out.println("----------------------------------------------------");
        }
    }

    public void update() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please enter ID of the book you wish to update: ");
        int id = Integer.parseInt(scanner.nextLine());

        List<Book> allBooks = bookController.findAllController();
        Book updateBook = allBooks.stream().filter(b -> b.getId() == id).findFirst().orElse(null);

        if (updateBook == null) {
            System.out.println(RED + "Book not found." + RESET);
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
            System.out.println(RED + "Update cancelled." + RESET);
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
            System.out.println(RED + "Delete cancelled." + RESET);
        }
    }

    public boolean proceedWithModification(Scanner scanner) {
        System.out.println("Do you want to proceed with this modification? (y/n): ");
        String confirmation = scanner.nextLine().toLowerCase();
        return confirmation.equals("y") || confirmation.equals("yes");
    }
}