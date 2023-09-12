package com.onlinebanking.team3.onlinebanking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@CrossOrigin("*")
public class UserController {
    @Autowired
    private UserService uService;

    @GetMapping("/welcome")
    public String demo() {
        return "Welcome User";
    }

    @PostMapping("/users")
    public User saveUser(@Validated @RequestBody User user) {
        try {
            User u = uService.saveUser(user);
            return u;

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();

            return null;
        }
    }

    @GetMapping("/users")

    public List<User> getAllUsers() {
        try {
            return uService.listAll();
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            return null;
        }
    }

}
