package by.rimza.library.controllers;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public interface Controller<E> {
    default void sendAsJSON(HttpServletResponse resp,String filterName, E element) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        SimpleBeanPropertyFilter theFilter = SimpleBeanPropertyFilter.serializeAll();
        FilterProvider filters = new SimpleFilterProvider().addFilter(filterName, theFilter);
        String respond = objectMapper.writer(filters).writeValueAsString(element);
        PrintWriter writer = resp.getWriter();
        writer.println(respond);
        writer.flush();
    }

    default void sendAsJSON(HttpServletResponse resp,String filterName,  List<E> elements) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        SimpleBeanPropertyFilter theFilter = SimpleBeanPropertyFilter.serializeAllExcept("books");
        FilterProvider filters = new SimpleFilterProvider().addFilter(filterName, theFilter);
        String respond = objectMapper.writer(filters).writeValueAsString(elements);
        PrintWriter writer = resp.getWriter();
        writer.println(respond);
        writer.flush();
    }

    default E fromJSON(HttpServletRequest req, Class<E> clazz) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        StringBuilder info = new StringBuilder();
        BufferedReader reader = req.getReader();
        String line;
        while ((line = reader.readLine()) != null) {
            info.append(line);
        }
        String finish = info.toString();
        return objectMapper.readValue(finish, clazz);
    }

    default int checkPathAndGetId(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String path = req.getPathInfo();
        if (path == null || path.equals("/")) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            return -1;
        }
        int id = Integer.parseInt(path.split("/")[1]);
        if (id <= 0) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            return -1;
        }
        return id;
    }

}
