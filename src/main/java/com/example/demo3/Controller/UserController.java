package com.example.demo3.Controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo3.Entity.User;
import com.example.demo3.Repository.UserRepository;


@RestController
@RequestMapping("/api/students")
@CrossOrigin(origins = "*")
public class UserController {

    private final UserRepository userRepo;

    // Dependency injection - constructor injection
    public UserController(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    // Inserting a new student
    @PostMapping
    public User addStudent(@RequestBody User user) {
        return userRepo.save(user);
    }

    // Getting all students
    @GetMapping
    public List<User> getStudents() {
        return userRepo.findAll();
    }
    @GetMapping("/{id}")
    public User getSingleStudent(@PathVariable Long id){
        //find if student with thatid present
        User existingUser = userRepo.findById(id).orElseThrow(() ->
    new ResponseStatusException(HttpStatus.NOT_FOUND, "Student with ID:"
    +id+ "not found!!"));

      return existingUser;
    }


    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable Long id){
        boolean existing = userRepo.existsById(id);
        if (!existing) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Student with ID:"
            +id+ "not found!!");
        }

        userRepo.deleteById(id);
    }

    @PutMapping("/{id}")
    public User updateStudent(@RequestBody User user, @PathVariable Long id){
        User exiUser = userRepo.findById(id).orElseThrow(() ->
        new ResponseStatusException(HttpStatus.NOT_FOUND, "Student with ID:"
        +id+ "not found!!"));

       if (user.getName()== null || user.getEmail() == null) {
            return exiUser;
       }

       exiUser.setName(user.getName());
       exiUser.setEmail(user.getEmail());

       return userRepo.save(exiUser);
    }


}
