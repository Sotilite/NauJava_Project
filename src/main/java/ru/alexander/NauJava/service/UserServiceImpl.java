package ru.alexander.NauJava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.alexander.NauJava.entity.User;
import ru.alexander.NauJava.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleService roleService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public void addUser(UserDetails userDetails) throws Exception {
        var existingUser = userRepository.findByLogin(userDetails.getUsername());
        if(existingUser != null) {
            throw new Exception("This user already exists");
        }

        var existingRole = roleService.findRoleByTitle("ADMIN");
        userRepository.save(
                new User("default",
                        userDetails.getUsername(),
                        passwordEncoder.encode(userDetails.getPassword()),
                        existingRole,
                        LocalDateTime.now()
                )
        );
    }

    @Override
    public User findUserByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    @Override
    public List<User> allUsers() {
        return (List<User>)userRepository.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        var user = findUserByLogin(login);
        if (user != null) {
            var roleTitle = user.getRole().getTitle();
            return new org.springframework.security.core.userdetails.User(
                    user.getLogin(), user.getPassword(),
                    List.of(new SimpleGrantedAuthority("ROLE_" + roleTitle)));
        } else {
            throw new UsernameNotFoundException("User not found");
        }
    }
}
