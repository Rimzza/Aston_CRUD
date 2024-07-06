import by.rimza.library.entities.BookEntity;
import by.rimza.library.entities.UserEntity;
import by.rimza.library.repositories.DAO.BookDAO;
import by.rimza.library.repositories.connectionDB.PrepareStatementDB;
import by.rimza.library.repositories.connectionDB.StatementDB;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class BookDAOTest {

    private static BookDAO bookDAO;
    private static BookEntity book1;
    private static BookEntity book2;

    @BeforeAll
    public static void createCon(){
        StatementDB statementDB = new StatementDB();
        PrepareStatementDB prepareStatementDB = new PrepareStatementDB();
        bookDAO = new BookDAO();
        bookDAO.setStatementDB(statementDB);
        bookDAO.setPrStatement(prepareStatementDB);
        book1 = BookEntity.builder().author("book1_author").title("book1_title").build();
        book2 = BookEntity.builder().author("book2_author").title("book2_title").build();
        bookDAO.create(book1);
        bookDAO.create(book2);
    }

    @Test
    public void createTest(){
        BookEntity book  = BookEntity.builder().author("Nikita").title("Yana").build();
        bookDAO.create(book);
        Assertions.assertEquals("Yana",bookDAO.read(book.getTitle()).getTitle(), "Title должен быть равен Yana");
    }

    @Test
    public void updateTest(){
        bookDAO.create(book1);
        BookEntity book = bookDAO.read(book1.getTitle());
        book.setAuthor("test_author");
        bookDAO.update(book.getId(),book);
    }

    @Test
    public void readTest(){
        BookEntity book = bookDAO.read(book2.getTitle());
        Assertions.assertEquals("book2_title",book.getTitle(), "Title должен быть равен book2_title");
    }



}
