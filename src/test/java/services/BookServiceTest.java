package services;

import by.rimza.library.dto.BookDto;
import by.rimza.library.entities.BookEntity;
import by.rimza.library.repositories.DAO.BookDAO;
import by.rimza.library.repositories.connectionDB.PrepareStatementDB;
import by.rimza.library.services.BookService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mockito;

import java.awt.print.Book;
import java.util.List;

public class BookServiceTest {


    private static BookService bookService;
    private static BookService bookServiceMock;
    private static BookDAO bookDAO;
    private static PrepareStatementDB pr;

    @BeforeAll
    public static void before(){
        bookDAO = Mockito.mock(BookDAO.class);
        pr = Mockito.mock(PrepareStatementDB.class);
        bookService = new BookService(new BookDAO());
        bookServiceMock = new BookService(bookDAO);

    }

    @ParameterizedTest()
    @CsvSource({
            "5,2",
            "6,7"
    })
    public void assignOwnerTest(int id, int idOwner){
        bookServiceMock.assignOwner(id, idOwner);
        Mockito.verify(bookDAO,Mockito.times(1))
                .assignOwner(id, idOwner);
    }

    @Test
    public void findAllTest(){
        List<BookDto> books = bookService.findAll();
        Assertions.assertFalse(books.isEmpty(), "Books should not be empty");
    }

    @Test
    public void findByIdTest(){
        BookDto bookDto = bookService.findById(1);
        Assertions.assertNotNull(bookDto, "Book should not be null");
    }

    @Test
    public void createBookTest(){
        BookDto bookDto = Mockito.mock(BookDto.class);
        Mockito.when(bookDto.getAuthor()).thenReturn("John Doe");
        Mockito.when(bookDto.getTitle()).thenReturn("Book Title");
        bookServiceMock.create(bookDto);
        Mockito.verify(bookDAO,Mockito.times(1)).create(Mockito.any());
    }

    @Test
    public void updateTest(){
        BookDto bookMock = Mockito.mock(BookDto.class);
        Mockito.when(bookMock.getAuthor()).thenReturn("Медведев");
        Mockito.when(bookMock.getTitle()).thenReturn("Обыкновенный великан");
        bookServiceMock.update(1, bookMock);
        Mockito.verify(bookDAO,Mockito.times(1))
                .update(Mockito.eq(1), Mockito.any(BookEntity.class));
    }

    @Test
    public void deleteTest(){
        bookServiceMock.delete(1);
        Mockito.verify(bookDAO,Mockito.times(1)).delete(1);
    }



    @Test
    public void removeOwnerTest(){
        bookServiceMock.removeOwner(Mockito.anyInt());
        Mockito.verify(bookDAO,Mockito.times(1)).removeOwner(Mockito.anyInt());
    }

}
