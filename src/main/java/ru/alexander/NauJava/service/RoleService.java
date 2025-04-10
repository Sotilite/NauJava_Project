package ru.alexander.NauJava.service;

import ru.alexander.NauJava.entity.Role;

public interface RoleService {
    /**
     * Находит роль по ее названию
     * @param roleTitle название роли в формате upperCase
     * */
    Role findRoleByTitle(String roleTitle);
}
