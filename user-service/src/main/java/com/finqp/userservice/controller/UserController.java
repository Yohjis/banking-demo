package com.finqp.userservice.controller;


import com.finqp.userservice.entity.Role;
import com.finqp.userservice.entity.User;
import com.finqp.userservice.entity.dto.UserDto;
import com.finqp.userservice.entity.UserRole;
import com.finqp.userservice.repo.RoleRepository;
import com.finqp.userservice.repo.UserRepository;
import com.finqp.userservice.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("api/auth/users")
@RestController
public class UserController {
    private final UserServiceImpl userService;
    private UserRepository userRepository;
    private RoleRepository roleRepository;


    @GetMapping("/users")
    public ResponseEntity<List<User>> getAll() {
        final List<User> users = userService.getAll();
        return ResponseEntity.ok(users);
    }


    @GetMapping("/{id}")
    public ResponseEntity<User> getById(@PathVariable long id) {
        try {
            User user = userService.findById(id);

            return ResponseEntity.ok(user);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/dto/{id}")
    public ResponseEntity<UserDto> user(@PathVariable long id) {
        User user = userService.findById(id);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        UserDto result = UserDto.fromUser(user);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }



    @PostMapping("/create")
    public ResponseEntity<Void> create(@RequestBody UserDto fromUser) {

        final String userUri = "/user" + new UserDto().getId();
        final String name = fromUser.getUsername();
        Role roleUser = roleRepository.findByName("ROLE_USER");
        List<Role> userRoles = new ArrayList<>();
        userRoles.add(roleUser);


        return ResponseEntity.created(URI.create(userUri)).build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UserDto> change(@PathVariable long id, @RequestBody UserDto userDto) {
        final String name = userDto.getUsername();
        final String email = userDto.getEmail();
        User user = userService.findById(id);
        UserDto result = UserDto.fromUser(user);
        if (result != null) {
            return new ResponseEntity<>(result, HttpStatus.OK);

        } else
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);


    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable long id) {
        userService.deleteUser(id);

        return ResponseEntity.noContent().build();
    }

}