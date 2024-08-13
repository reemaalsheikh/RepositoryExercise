package com.example.repositoryexercise.Repository;

import com.example.repositoryexercise.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User findUserById(Integer id);


//• Check if username and password are correct
User findUserByUserNameAndPassword(String userName, String password);

//• Get user by email
User findUserByEmail(String email);

//• Get All users with specific role
 @Query("select u from User u where u.role=?1")
 List<User> findUserByRole(String role);

//• Get All users with specific age or above
@Query("select u from User u where u.age>=?1")
List<User> findUserByAgeGreaterThanEqual(int age);






}
