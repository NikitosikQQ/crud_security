package com.example.demo.config;

import com.example.demo.user.Role;
import com.example.demo.user.User;
import com.example.demo.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Configuration
public class UserConfiguration {

    @Bean
    CommandLineRunner commandLineRunner(UserRepository userRepository) {
        Role userRole = new Role("ROLE_USER");
        Role adminRole = new Role("ROLE_ADMIN");
        Set<Role> roleUser = new HashSet<>();
        roleUser.add(userRole);
        Set<Role> roleAdmin = new HashSet<>();
        roleAdmin.add(adminRole);
        roleAdmin.add(userRole);
        return args -> {

            User user1 = new User("User", "Nikita@mail.ru", 22, "user", roleUser);
            User admin = new User("Admin", "Alexander@mail.ru", 20, "admin", roleAdmin);

            userRepository.saveAll(List.of(user1, admin));
        };
    }
}
