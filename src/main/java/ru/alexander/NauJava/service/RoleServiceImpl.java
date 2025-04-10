package ru.alexander.NauJava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.alexander.NauJava.entity.Role;
import ru.alexander.NauJava.repository.RoleRepository;

@Service
public class RoleServiceImpl implements  RoleService {
    @Autowired
    RoleRepository roleRepository;

    @Override
    public Role findRoleByTitle(String roleTitle) {
        return roleRepository.findByTitle(roleTitle);
    }
}
