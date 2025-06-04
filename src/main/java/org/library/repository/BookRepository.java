package org.library.repository;

import org.library.config.DBManager;
import org.library.model.Book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookRepository {

        private static final String RESET = "\u001B[0m";
        private static final String RED = "\u001B[31m";
        private static final String GREEN = "\u001B[92m";

        private Connection connection;

        public void saveBook(Book book) {

            String querySQLCreate = "INSERT INTO books (title, authors, description, isbn, genre, year) VALUES (?, ?, ?, ?, ?, ?)";

            try {
                connection = DBManager.initConnection();

                PreparedStatement statement = connection.prepareStatement(querySQLCreate);

                statement.setString(1, book.getTitle());
                statement.setString(2, book.getAuthors());
                statement.setString(3, book.getDescription());
                statement.setString(4, book.getIsbn());
                statement.setString(5, book.getGenre());
                statement.setInt(6, book.getYear());
                statement.execute();

                System.out.println(GREEN + "Book created" + RESET);

            } catch (SQLException exception) {
                throw new RuntimeException(exception.getMessage());
            } finally {
                DBManager.closeConnection();
            }
        }

        public List<Book> findAll() {
            List<Book> books = new ArrayList<>();

            String querySQLSelectALl = "SELECT * FROM books";

            try {
                connection = DBManager.initConnection();

                PreparedStatement statement = connection.prepareStatement(querySQLSelectALl);
                ResultSet response = statement.executeQuery();

                while(response.next()) {
                    int id = response.getInt("id");
                    String title = response.getString("title");
                    String authors = response.getString("authors");
                    String description = response.getString("description");
                    String isbn = response.getString("isbn");
                    String genre = response.getString("genre");
                    int year = response.getInt("year");

                    Book book = new Book(id, title, authors, description, isbn, genre, year);

                    books.add(book);
                }
            } catch (SQLException exception) {
                System.out.println(exception.getMessage());
            } finally {
                DBManager.closeConnection();
            }
            return books;
        }

        public void update(int id, Book book) {

            String querySQLUpdate = "UPDATE books SET title = ?, authors = ?, description = ?, isbn = ?, genre = ?, year = ? WHERE id = ?";

            try {
                connection = DBManager.initConnection();

                PreparedStatement statement = connection.prepareStatement(querySQLUpdate);

                statement.setString(1, book.getTitle());
                statement.setString(2, book.getAuthors());
                statement.setString(3, book.getDescription());
                statement.setString(4, book.getIsbn());
                statement.setString(5, book.getGenre());
                statement.setInt(6, book.getYear());
                statement.setInt(7, id);

                int rowsUpdated = statement.executeUpdate();

                if (rowsUpdated > 0){
                    System.out.println(GREEN + "Book updated successfully" + RESET);
                } else {
                    System.out.println(RED + "Book not found" + RESET);
                }

            } catch (SQLException exception) {
                throw new RuntimeException(exception.getMessage());
            } finally {
                DBManager.closeConnection();
            }

        }

    public void delete(int id) {

        String querySQLDelete = "DELETE FROM books WHERE id = ?";

        try {
            connection = DBManager.initConnection();

            PreparedStatement statement = connection.prepareStatement(querySQLDelete);

            statement.setInt(1, id);

            int rowsDeleted = statement.executeUpdate();

            if (rowsDeleted > 0) {
                System.out.println(GREEN + "Book deleted successfully" + RESET);
            } else {
                System.out.println(RED + "Book not found" + RESET);
            }

        } catch (SQLException exception) {
            throw new RuntimeException(exception.getMessage());
        } finally {
            DBManager.closeConnection();
        }
    }
}
