package com.example.repositoryexercise.Controller;

import com.example.repositoryexercise.ApiResponse.ApiResponse;
import com.example.repositoryexercise.Model.User;
import com.example.repositoryexercise.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;

//• Get all the Users
@GetMapping("/get")
public ResponseEntity getAllUsers() {
    return ResponseEntity.status(200).body(userService.getAllUsers());
}

//• Add new User
@PostMapping("/add")
    public ResponseEntity addNewUser(@Valid @RequestBody User user, Errors errors) {
    if (errors.hasErrors()) {
        String message =errors.getFieldError().getDefaultMessage();
        return ResponseEntity.status(400).body(message);
    }
    userService.addUser(user);
    return ResponseEntity.status(200).body(new ApiResponse("User Successfully added!"));
    }

//• Update User
@PutMapping("/update/{id}")
public  ResponseEntity updateUser(@PathVariable Integer id,@Valid @RequestBody User user , Errors errors){
    if (errors.hasErrors()) {
        String message = errors.getFieldError().getDefaultMessage();
        return ResponseEntity.status(400).body(message);
    }
    userService.updateUser(id,user);
    return ResponseEntity.status(200).body(new ApiResponse("User Successfully updated!"));
}

//• Delete User
@DeleteMapping("/delete/{id}")
public ResponseEntity deleteUser(@PathVariable Integer id){
    userService.deleteUser(id);
    return ResponseEntity.status(200).body(new ApiResponse("User Successfully deleted!"));
}

//• Check if username and password are correct
 @GetMapping("/check/{username}/{password}")
 public ResponseEntity checkUsernameAndPassword(@PathVariable String username, @PathVariable String password){
    User u = userService.checkUserNameAndPassword(username,password);
    return ResponseEntity.status(200).body(u);
    }

//• Get user by email
    @GetMapping("/get/{email}")
    public ResponseEntity findUserByEmail(@PathVariable String email){
    User u = userService.getUserByEmail(email);
    return ResponseEntity.status(200).body(u);
    }

//• Get All users with specific role
    @GetMapping("/find/role/{role}")
    public ResponseEntity findUserByRole(@PathVariable String role){
    List<User> userByRole = userService.findUserByRole(role);
    return ResponseEntity.status(200).body(userByRole);
    }

//• Get All users with specific age or above
@GetMapping("/find/age/{age}")
public ResponseEntity findUserByAge(@PathVariable int age){
    List<User> userByAge = userService.findUserByAge(age);
    return ResponseEntity.status(200).body(userByAge);
}

}
