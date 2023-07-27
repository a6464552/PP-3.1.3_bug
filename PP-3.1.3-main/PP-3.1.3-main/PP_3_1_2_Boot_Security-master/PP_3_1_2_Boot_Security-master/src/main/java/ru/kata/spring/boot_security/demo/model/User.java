package ru.kata.spring.boot_security.demo.model;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.Collection;
import java.util.Set;


@Entity
@Table(name = "users_security")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", unique = true)
    @Pattern(regexp = "^[a-zA-Zа-яА-Я]+$", message = "Поле Имя может содержать только буквы")
    private String username;

    @Column(name = "last_name")
    @Pattern(regexp = "^[a-zA-Zа-яА-Я]+$", message = "Поле Фамилия может содержать только буквы")
    private String lastName;

    @Column(name = "age")
    @Pattern(regexp = "[1-9][0-9]*", message = "Поле Возраст должно быть больше ноля и может содержать только цифры")
    private int age;

    @Column(name = "password")
    private String password;

//    @ManyToMany(fetch = FetchType.LAZY)
//    private Set<Role> roles;

    @Fetch(FetchMode.JOIN)
    @ManyToMany
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "users_id"),
            inverseJoinColumns = @JoinColumn(name = "roles_id"))
    private Set<Role> roles;

    public User() {

    }

    public User(String username, String lastName, int age, String password, Set<Role> roles) {
        this.username = username;
        this.lastName = lastName;
        this.age = age;
        this.password = password;
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
