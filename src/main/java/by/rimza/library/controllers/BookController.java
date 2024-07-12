package by.rimza.library.controllers;

import by.rimza.library.dto.BookDto;
import by.rimza.library.repositories.DAO.BookDAO;
import by.rimza.library.services.BookService;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/books/*")
public class BookController extends HttpServlet implements Controller<BookDto> {

    private static final BookService bookService = new BookService(new BookDAO());


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String pathInto = req.getPathInfo();
        if (pathInto == null || pathInto.equals("/")) {
            List<BookDto> books = bookService.findAll();
            sendAsJSON(resp, "bookFilter", books);
            return;
        }


        int id = checkPathAndGetId(req, resp);
        if (id == -1) {
            return;
        }
        BookDto bookDto = bookService.findById(id);
        if (bookDto == null) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        sendAsJSON(resp, "bookFilter", bookDto);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
            BookDto book = fromJSON(req, BookDto.class);
            bookService.create(book);
            resp.sendRedirect("/books");
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id = checkPathAndGetId(req, resp);
        if (id == -1) {
            return;
        }
        BookDto book = fromJSON(req, BookDto.class);
        bookService.update(id, book);
        resp.sendRedirect("/books");
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id = checkPathAndGetId(req, resp);
        if (id == -1) {
            return;
        }
        bookService.delete(id);
        resp.sendRedirect("/books");
    }
}
