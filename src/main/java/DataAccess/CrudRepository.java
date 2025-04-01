package DataAccess;

import java.util.List;

public interface CrudRepository<T, ID> {
    void create(T entity);
    T read(ID id);
    void update(T entity);
    void delete(ID id);
    boolean contains(ID id);
    List<T> getAll();
}
