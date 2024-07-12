import by.rimza.library.entities.UserEntity;
import by.rimza.library.repositories.DAO.UserDAO;
import by.rimza.library.repositories.connectionDB.PrepareStatementDB;
import by.rimza.library.repositories.connectionDB.StatementDB;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;


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
         /*user1 = UserEntity.builder().firstName("Nikita")
                .secondName("Egorov").patronymic("test").age(23).build();
       user2 = UserEntity.builder().firstName("TEst")
                .secondName("TEst").patronymic("test").age(15).build();
       userDAO.create(user1);
       userDAO.create(user2);*/


    }

   /* @Test
    public void testCreate() {
        Assertions.assertEquals(23,userDAO.index().get(0).getAge(),"Возраст должен быть равен 23.");
    }*/

   /* @Test
    public void testIndex() {
        List<UserEntity> users = userDAO.index();
        Assertions.assertEquals(2, users.size(), "Размер должен быть равен 2.");
    }*/

    /*@Test
    public void testRead(){
        Assertions.assertEquals("Nikita", userDAO.read(1).getFirstName());
        System.out.println(userDAO.read("Nikita"));
    }*/

    @ParameterizedTest(name = "Тест с параметрами")
    @ValueSource(ints = {3,11})
    public void testRead(int id){
        System.out.println(userDAO.read(id));
    }







    @AfterAll
    public static void clearDB(){
        userDAO.clear();

    }
}
