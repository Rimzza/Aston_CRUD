package by.rimza.library.dto;


import com.fasterxml.jackson.annotation.JsonFilter;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@JsonFilter("userFilter")
public class UserDto extends Dto{
    private String firstName;
    private String secondName;
    private String patronymic;
    private int age;
    private List<BookDto> books;


}
