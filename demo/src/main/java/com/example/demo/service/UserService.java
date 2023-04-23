package com.example.demo.service;

import com.example.demo.user.Role;
import com.example.demo.user.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service

public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getListOfUsers() {
        return userRepository.findAll();
    }

    public User findUserById(Long id) {
        return userRepository.getOne(id);
    }

    public User saveUser(User user) {
        User checkUsername = userRepository.findUserByName(user.getName());
        if(checkUsername != null){
            return null;
        }
        user.setRoles(Collections.singleton(new Role("ROLE_USER")));
        return userRepository.save(user);
    }

    public User findUserByName(String name) {
        return userRepository.findUserByName(name);
    }

    public void deleteUserById(Long id) {
        userRepository.deleteById(id);

    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByName(username);
        if(user == null){
            throw new UsernameNotFoundException("User not found:(");
        }
        return user;
    }
}
