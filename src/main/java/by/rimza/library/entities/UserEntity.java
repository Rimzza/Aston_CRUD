package by.rimza.library.entities;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@ToString
public class UserEntity {
    @ToString.Exclude
    private int id;
    private String firstName;
    private String secondName;
    private String patronymic;
    private int age;
    private List<BookEntity> books;

}
