package by.rimza.library.repositories.DAO;


import java.util.List;

public interface DAO<E> {
    List<E> index();

    E read(int id);

    boolean delete(int id);

    boolean create(E e);

    boolean update(int id, E e);

   void clear();


}
