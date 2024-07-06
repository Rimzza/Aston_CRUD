package by.rimza.library.repositories.DAO;

import by.rimza.library.entities.BookEntity;
import by.rimza.library.repositories.connectionDB.PrepareStatementDB;
import by.rimza.library.repositories.connectionDB.StatementDB;
import by.rimza.library.repositories.mappers.MapperUtils;
import lombok.Setter;
import lombok.SneakyThrows;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Setter
public class BookDAO implements DAO<BookEntity> {

    private StatementDB statementDB;
    private PrepareStatementDB prStatement;

    @SneakyThrows
    @Override
    public List<BookEntity> index() {
        checkStatement();
        List<BookEntity> books = new ArrayList<>();
        Statement statement = statementDB.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from books");
        while (resultSet.next()) {
            books.add(MapperUtils.toBookEntity(resultSet));
        }
        return books;
    }

    @SneakyThrows
    @Override
    public BookEntity read(int id) {
        checkPrStatement();
        BookEntity book = null;
        PreparedStatement ps = prStatement.readBook(id);
        ResultSet resultSet = ps.executeQuery();
        while (resultSet.next()) {
            book = MapperUtils.toBookEntity(resultSet);
        }
        return book;
    }

    @SneakyThrows
    public BookEntity read(String title){
        checkPrStatement();
        BookEntity book = null;
        PreparedStatement ps = prStatement.readBook(title);
        ResultSet resultSet = ps.executeQuery();
        while (resultSet.next()) {
            book = MapperUtils.toBookEntity(resultSet);
        }
        return book;
    }


    @SneakyThrows
    @Override
    public boolean delete(int id) {
        checkPrStatement();
        PreparedStatement ps = prStatement.deleteBook(id);
        ps.executeQuery();
        return false;
    }

    @SneakyThrows
    @Override
    public boolean create(BookEntity bookEntity) {
        checkPrStatement();
        PreparedStatement ps = prStatement.createBook(bookEntity);
        ps.executeUpdate();
        return true;
    }

    @SneakyThrows
    @Override
    public boolean update(int id, BookEntity updatedBook) {
        checkPrStatement();
        PreparedStatement ps = prStatement.updateBook(id, updatedBook);
        ps.executeUpdate();
        return true;
    }

    @SneakyThrows
    @Override
    public void clear() {
        checkStatement();
        statementDB.createStatement().execute("truncate table books");
    }

    private void checkStatement() {
        if (statementDB == null)
            statementDB = new StatementDB();
    }

    private void checkPrStatement() {
        if (prStatement == null)
            prStatement = new PrepareStatementDB();
    }
}
