import by.rimza.library.entities.BookEntity;
import by.rimza.library.entities.UserEntity;
import by.rimza.library.repositories.DAO.BookDAO;
import by.rimza.library.repositories.connectionDB.PrepareStatementDB;
import by.rimza.library.repositories.connectionDB.StatementDB;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class BookDAOTest {

    private static BookDAO bookDAO;
    private static BookEntity book1;
    private static BookEntity book2;
    private static UserEntity user1;

    @BeforeAll
    public static void createCon(){
        StatementDB statementDB = new StatementDB();
        PrepareStatementDB prepareStatementDB = new PrepareStatementDB();
        bookDAO = new BookDAO();
        bookDAO.setStatementDB(statementDB);
        bookDAO.setPrStatement(prepareStatementDB);
        book1 = BookEntity.builder().author("Успенский").title("Красная шапочка").build();
        book2 = BookEntity.builder().author("Ребекка").title("Железное пламя").build();
        bookDAO.create(book1);
        bookDAO.create(book2);
    }

    @Test
    public void createTest(){
        Assertions.assertEquals("Красная шапочка",bookDAO.read(book1.getTitle()).getTitle(), "Title должен быть равен Красная шапочка");
    }

    @Test
    public void updateTest(){
        BookEntity book = bookDAO.read(book2.getTitle());
        book.setAuthor("Не Ребекка");
        bookDAO.update(book.getId(),book);
    }

    /*@Test
    public void deleteTest(){
        bookDAO.delete(bookDAO.read(book2.getTitle()).getId());
    }*/

    @Test
    public void assignOwnerTest(){
        UserEntity userMock = Mockito.mock(UserEntity.class);
        Mockito.when(userMock.getId()).thenReturn(1);
        bookDAO.assignOwner(1,userMock.getId());

    }

    @AfterAll
    public static void clearDB(){
        bookDAO.clear();
    }




}
