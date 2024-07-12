package by.rimza.library.services;

import by.rimza.library.dto.BookDto;
import by.rimza.library.entities.BookEntity;
import by.rimza.library.repositories.DAO.BookDAO;
import by.rimza.library.repositories.mappers.MapperUtils;
import lombok.RequiredArgsConstructor;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class BookService {

    private final BookDAO bookDAO;

    public List<BookDto> findAll(){
        return bookDAO.index()
                .stream().map(MapperUtils::toBookDto)
                .collect(Collectors.toList());
    }

    public BookDto findById(int id){
        return MapperUtils.toBookDto(bookDAO.read(id));
    }

    public void create(BookDto bookDto){
        bookDAO.create(MapperUtils.toBookEntity(bookDto));
    }

    public void update(int id,BookDto bookDto){
        BookEntity book = MapperUtils.toBookEntity(bookDto);
        bookDAO.update(id,book);
    }

    public void delete(int id){
        bookDAO.delete(id);
    }

    public void assignOwner(int id, int idOwner){
        bookDAO.assignOwner(id,idOwner);
    }

    public void removeOwner(int id){
        bookDAO.removeOwner(id);
    }



}
