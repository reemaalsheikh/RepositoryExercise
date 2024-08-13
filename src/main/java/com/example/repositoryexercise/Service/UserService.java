package com.example.repositoryexercise.Service;

import com.example.repositoryexercise.ApiResponse.ApiException;
import com.example.repositoryexercise.Model.User;
import com.example.repositoryexercise.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

//• Get all the Users
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

//• Add new User
    public void addUser (User user) {
        userRepository.save(user);
    }

//• Update User
    public void updateUser (Integer id,User user) {
        User u = userRepository.findUserById(id);
        if (u == null) {
            throw new ApiException("User not found");
        }
        u.setName(user.getName());
        u.setUserName(user.getUserName());
        u.setPassword(user.getPassword());
        u.setEmail(user.getEmail());
        u.setRole(user.getRole());
        u.setAge(user.getAge());
        userRepository.save(u);
    }

//• Delete User
    public void deleteUser(Integer id) {
        User u = userRepository.findUserById(id);
        if (u == null) {
            throw new ApiException("User not found");
        }
        userRepository.delete(u);
    }

//• Check if username and password are correct
    public User checkUserNameAndPassword (String username, String password) {
    User u = userRepository.findUserByUserNameAndPassword(username, password);
    if (u == null) {
        throw new ApiException("User name and password not found");
    }
    return u;
    }

//• Get user by email
    public User getUserByEmail(String email) {
        User u = userRepository.findUserByEmail(email);
        if (u == null) {
            throw new ApiException("User email not found!");
        }
        return u;
    }
//• Get All users with specific role
    public List<User> findUserByRole(String role) {
        List<User> userByRole = userRepository.findUserByRole(role);
        if (userByRole.isEmpty()) {
            throw new ApiException("User role not found");
        }
        return userByRole;
    }

//• Get All users with specific age or above
    public List<User> findUserByAge(int age) {
       List<User> userByAge = userRepository.findUserByAgeGreaterThanEqual(age);
       if (userByAge.isEmpty()) {
           throw new ApiException("User age not found");
       }
       return userByAge;
    }
}
