import by.rimza.library.entities.UserEntity;
import by.rimza.library.repositories.DAO.UserDAO;
import by.rimza.library.repositories.connectionDB.ConnectionToDB;
import by.rimza.library.repositories.connectionDB.PrepareStatementDB;
import by.rimza.library.repositories.connectionDB.StatementDB;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


import java.util.List;

public class UserDAOTest {

    private static UserDAO userDAO;
    private static UserEntity user1;
    private static UserEntity user2;


    @BeforeAll
    public static void createCon() {
        StatementDB statementDB = new StatementDB();
        PrepareStatementDB prStatement = new PrepareStatementDB();
        userDAO = new UserDAO();
        userDAO.setPrStatement(prStatement);
        userDAO.setStatementDB(statementDB);
         user1 = UserEntity.builder().firstName("Nikita")
                .secondName("Egorov").patronymic("test").age(23).build();
       user2 = UserEntity.builder().firstName("TEst")
                .secondName("TEst").patronymic("test").age(15).build();


    }

    @Test
    public void testCreate() {
        userDAO.create(user1);
        userDAO.create(user2);
        Assertions.assertEquals(23,userDAO.index().get(0).getAge(),"Возраст должен быть равен 23.");
    }

    @Test
    public void testIndex() {
        testCreate();
        List<UserEntity> users = userDAO.index();
        Assertions.assertEquals(2, users.size(), "Размер должен быть равен 2.");
    }

    @Test
    public void testRead(){
        System.out.println(userDAO.read("Nikita"));
    }







    @AfterAll
    public static void clearDB(){
        userDAO.clear();

    }
}
