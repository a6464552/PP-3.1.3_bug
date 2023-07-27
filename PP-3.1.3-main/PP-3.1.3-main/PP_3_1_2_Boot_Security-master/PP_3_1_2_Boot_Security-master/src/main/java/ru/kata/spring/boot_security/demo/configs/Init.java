package ru.kata.spring.boot_security.demo.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

//@Component
//public class Init {
//
//    private final UserRepository userRepository;
//    private final RoleService roleService;
//
//    @Autowired
//    public Init(UserRepository userRepository, RoleService roleService) {
//        this.userRepository = userRepository;
//        this.roleService = roleService;
//    }
//
//
//    @PostConstruct
//    public void setInitData() {
//        Role userRole = new Role();
//        userRole.setName("ROLE_USER");
//        Role adminRole = new Role();
//        adminRole.setName("ROLE_ADMIN");
//        roleService.saveRole(userRole);
//        roleService.saveRole(adminRole);
//
//        User user = new User();
//        user.setUsername("user");
//        user.setLastName("Aev");
//        user.setAge(30);
//        user.setPassword("user");
//        user.setRoles(new HashSet<Role>() {{
//            add(userRole);
//        }});
//        userRepository.save(user);
//
//        User admin = new User();
//        admin.setUsername("admin");
//        admin.setLastName("Bev");
//        admin.setAge(35);
//        admin.setPassword("admin");
//        admin.setRoles(new HashSet<Role>() {{
//            add(userRole);
//            add(adminRole);
//        }});
//        userRepository.save(admin);
//    }
//}

@Component
public class Init {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public Init(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @PostConstruct
    public void initUsers() {

        Role adminRole = new Role("ADMIN");
        Set<Role> adminRoles = new HashSet<>();
        adminRoles.add(adminRole);

        roleService.saveRole(adminRole);

        Role userRole = new Role("USER");
        Set<Role> userRoles = new HashSet<>();
        userRoles.add(userRole);

        roleService.saveRole(userRole);

        User admin = new User("admin", "admin", 20, "admin", adminRoles);

        userService.add(admin);

        User user = new User("user", "user", 30, "user", userRoles);

        userService.add(user);
    }
}
