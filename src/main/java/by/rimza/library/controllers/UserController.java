package by.rimza.library.controllers;

import by.rimza.library.dto.UserDto;
import by.rimza.library.repositories.DAO.UserDAO;
import by.rimza.library.services.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/users/*")
public class UserController extends HttpServlet implements Controller<UserDto> {

    private static final UserService userService = new UserService(new UserDAO());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInto = req.getPathInfo();
        if (pathInto == null || pathInto.equals("/")) {
            List<UserDto> users = userService.findAll();
            sendAsJSON(resp, "userFilter", users);
            return;
        }

        int id = checkPathAndGetId(req, resp);
        if (id == -1)
            return;
        UserDto user = userService.findById(id);
        if (user == null) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        sendAsJSON(resp,"userFilter", user);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDto user = fromJSON(req, UserDto.class);
        userService.create(user);
        resp.sendRedirect("/users");
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = checkPathAndGetId(req, resp);
        if (id == -1)
            return;
        UserDto user = fromJSON(req, UserDto.class);
        userService.update(id, user);
        resp.sendRedirect("/users/");
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = checkPathAndGetId(req, resp);
        if (id == -1)
            return;
        userService.delete(id);
        resp.sendRedirect("/users");
    }
}
