package ru.alexander.NauJava.repository;

import org.springframework.data.repository.CrudRepository;
import ru.alexander.NauJava.entity.Role;

public interface RoleRepository extends CrudRepository<Role, Long> {
    /**
     * Находит роль по ее названию
     * @param roleTitle название роли
     */
    Role findByTitle(String roleTitle);
}
