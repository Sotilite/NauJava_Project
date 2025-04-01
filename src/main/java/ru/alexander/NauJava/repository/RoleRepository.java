package ru.alexander.NauJava.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.alexander.NauJava.entity.Role;

@RepositoryRestResource(path = "roles")
public interface RoleRepository extends CrudRepository<Role, Long> {
    /**
     * Находит роль по ее названию
     * @param roleTitle название роли
     */
    Role findByTitle(String roleTitle);
}
