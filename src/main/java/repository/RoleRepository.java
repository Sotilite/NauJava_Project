package repository;

import entity.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Long> {
    /**
     * Находит роль по наименованию
     * @param roleName наимменование роли
     * */
    Role findByName(String roleName);
}
