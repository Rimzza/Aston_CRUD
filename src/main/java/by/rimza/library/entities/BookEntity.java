package by.rimza.library.entities;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@Setter
@Getter
@ToString
public class BookEntity {
    @ToString.Exclude
    private int id;
    private String author;
    private String title;
    private boolean taken;
    private int who_taken;


}
