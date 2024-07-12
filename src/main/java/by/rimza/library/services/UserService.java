package by.rimza.library.services;

import by.rimza.library.dto.UserDto;
import by.rimza.library.entities.UserEntity;
import by.rimza.library.repositories.DAO.UserDAO;
import by.rimza.library.repositories.mappers.MapperUtils;
import lombok.RequiredArgsConstructor;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class UserService {

    private  final UserDAO userDAO;

    public List<UserDto> findAll(){
        return userDAO.index().stream()
                .map(MapperUtils::toUserDto)
                .collect(Collectors.toList());
    }

    public UserDto findById(int id){
        UserDto user = MapperUtils.toUserDto(userDAO.read(id));
        return user;
    }

    public void create(UserDto user){
        userDAO.create(MapperUtils.toUserEntity(user));
    }

    public void delete(int id){
        userDAO.delete(id);
    }

    public void update(int id,UserDto user){
        UserEntity userr = MapperUtils.toUserEntity(user);
        userDAO.update(id, userr);
    }




}
