package ru.kata.spring.boot_security.demo.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserDaoImpl implements UserDao {

    private final UserRepository userRepository;


    @Autowired
    public UserDaoImpl(UserRepository userRepository) {
        this.userRepository = userRepository;

    }

    @Override
    public void add(User user) {
        userRepository.save(user);

    }

    @Override
    public void delete(long id) {
        userRepository.deleteById(id);

    }

    @Override
    public User change(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> listUsers() {
        return userRepository.findAll();
    }

    @Override
    public User findUserById(long id) {
        Optional<User> foundUser = userRepository.findById(id);
        return foundUser.orElse(null);
    }

    @Override
    public User findByName(String username) {
        Optional<User> foundUser = userRepository.findByUsername(username);
        return foundUser.orElse(null);
    }
}
