package by.rimza.library.entities;

import lombok.*;

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

   /* @Builder
    public UserEntity(String firstName, String secondName, String patronymic, int age) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.patronymic = patronymic;
        this.age = age;
    }*/
}
