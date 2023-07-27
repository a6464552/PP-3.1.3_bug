package ru.kata.spring.boot_security.demo.dao;

import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

@Service
public interface RoleDao {
    List<Role> findAll();
    void saveRole(Role role);
}
