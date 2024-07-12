package by.rimza.library.repositories.mappers;

import by.rimza.library.dto.BookDto;
import by.rimza.library.dto.UserDto;
import by.rimza.library.entities.BookEntity;
import by.rimza.library.entities.UserEntity;
import lombok.SneakyThrows;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MapperUtils {

    @SneakyThrows
    public static UserEntity toUserEntity(ResultSet resultSet) {
        if (isNull(resultSet))
            return null;

        return UserEntity.builder().id(resultSet.getInt(1))
                .firstName(resultSet.getString(2))
                .secondName(resultSet.getString(3))
                .patronymic(resultSet.getString(4))
                .age(resultSet.getInt(5))
                .build();
    }

    @SneakyThrows
    public static BookEntity toBookEntity(ResultSet resultSet, boolean isFromJoin) {
        if (isNull(resultSet))
            return null;
        int i = 0;

        if (isFromJoin)
            i = 5;

        return BookEntity.builder().id(resultSet.getInt(1 + i))
                .author(resultSet.getString(2 + i))
                .title(resultSet.getString(3 + i))
                .taken(resultSet.getBoolean(4 + i))
                .who_taken(resultSet.getInt(5 + i))
                .build();
    }

    public static BookDto toBookDto(BookEntity bookEntity) {
        if (isNull(bookEntity))
            return null;
        BookDto book = new BookDto();
        book.setAuthor(bookEntity.getAuthor());
        book.setTitle(bookEntity.getTitle());
        return book;
    }

    public static UserDto toUserDto(UserEntity userEntity) {
        if (isNull(userEntity))
            return null;

        UserDto user = new UserDto();
        user.setFirstName(userEntity.getFirstName());
        user.setSecondName(userEntity.getSecondName());
        user.setPatronymic(userEntity.getPatronymic());
        user.setAge(userEntity.getAge());
        if (userEntity.getBooks() !=null) {
            List<BookDto> books = new ArrayList<>();
            for (BookEntity bookEntity : userEntity.getBooks()) {
                books.add(toBookDto(bookEntity));
            }
            user.setBooks(books);
        }
        return user;
    }

    public static boolean isNull(Object object) {
        return object == null;
    }

    public static UserEntity toUserEntity(UserDto userDto) {
        if (isNull(userDto))
            return null;

        return UserEntity.builder().firstName(userDto.getFirstName())
                .secondName(userDto.getSecondName())
                .patronymic(userDto.getPatronymic())
                .age(userDto.getAge())
                .build();
    }

    public static BookEntity toBookEntity(BookDto bookDto) {
        if (isNull(bookDto))
            return null;

        return BookEntity.builder().author(bookDto.getAuthor())
                .title(bookDto.getTitle()).build();
    }
}
