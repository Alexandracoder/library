package controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.library.controller.BookController;
import org.library.model.Book;
import org.library.repository.BookRepository;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class BookControllerTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookController bookController;

    @Test
    void saveBookController_shouldCallRepository() {
        Book book = new Book(1, "Hunger Games", "Suzanne Collins","this is a description","1234567891234","young adult literature", 2008);

        bookController.saveBookController(book);

        verify(bookRepository, times(1)).saveBook(book);
    }

    @Test
    void findAllController_shouldCallRepository(){
        bookController.findAllController();

        verify(bookRepository, times(1)).findAll();
    }

    @Test
    void updateBookController_shouldCallRepository(){
        int id = 1;
        Book book = new Book(1, "Hunger Games", "Suzanne Collins","this is a description","1234567891234","young adult literature", 2008);

        bookController.updateBookController(id, book);

        verify(bookRepository, times(1)).update(id, book);
    }

    @Test
    void deleteBookController_shouldCallRepository(){
        int id = 1;

        bookController.deleteBookController(id);

        verify(bookRepository, times(1)).delete(id);
    }
}
