package by.rimza.library.repositories.mappers;

import by.rimza.library.entities.UserEntity;
import lombok.SneakyThrows;

import java.sql.ResultSet;

public class MapperUtils {

    @SneakyThrows
    public static UserEntity toUserEntity(ResultSet resultSet){
        return UserEntity.builder().id(resultSet.getInt(1))
                .firstName(resultSet.getString(2))
                .secondName(resultSet.getString(3))
                .patronymic(resultSet.getString(4))
                .age(resultSet.getInt(5))
                .build();

    }
}
