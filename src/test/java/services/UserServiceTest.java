package services;

import by.rimza.library.dto.UserDto;
import by.rimza.library.entities.UserEntity;
import by.rimza.library.repositories.DAO.UserDAO;
import by.rimza.library.services.UserService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;
import java.util.List;

public class UserServiceTest {

    private static UserService userService;
    private static UserService userServiceMock;
    private static UserDAO userDAO;

    @BeforeAll
    public static void before(){
        userDAO = Mockito.mock(UserDAO.class);
        userService = new UserService(new UserDAO());
        userServiceMock = new UserService(userDAO);
    }

    @SneakyThrows
    @Test
    public void findAllTest(){
        List<UserDto> users = userService.findAll();
        Assertions.assertFalse(users.isEmpty(), "Пользователей должно быть как минимум 1.");
    }

    @ParameterizedTest()
    @ValueSource(ints = {1})
    public void findById(int id){
        UserDto user = userService.findById(id);
        Assertions.assertNotNull(user,"Пользователь не должен быть равен null");
    }

    @Test
    public void createTest(){
        UserDto userMock = Mockito.mock(UserDto.class);
        Mockito.when(userMock.getFirstName()).thenReturn("Elena");
        Mockito.when(userMock.getSecondName()).thenReturn("Egorova");
        Mockito.when(userMock.getPatronymic()).thenReturn("Viktorovna");
        Mockito.when(userMock.getAge()).thenReturn(46);

        userServiceMock.create(userMock);
        Mockito.verify(userDAO, Mockito.times(1)).create(Mockito.any(UserEntity.class));
    }

    @Test
    public void updateTest(){
        UserDto userMock = Mockito.mock(UserDto.class);
        Mockito.when(userMock.getFirstName()).thenReturn("ElenaUpdated");
        Mockito.when(userMock.getSecondName()).thenReturn("EgorovaUpdated");
        Mockito.when(userMock.getPatronymic()).thenReturn("ViktorovnaUpdated");
        Mockito.when(userMock.getAge()).thenReturn(46);

        userService.update(12,userMock);
        Mockito.verify(userMock, Mockito.times(1)).getPatronymic();
    }



    @Test
    public void deleteTest(){
        userServiceMock.delete(11);
        Mockito.verify(userDAO, Mockito.times(1)).delete(11);

    }




}
