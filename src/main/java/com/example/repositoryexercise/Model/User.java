package com.example.repositoryexercise.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Check;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
//@Check()
public class User {

//ID :Cannot be null
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY )
 private Integer id;

// name : Cannot be null Length more than 4
 @NotEmpty(message = "Name should not be Empty!")
 @Size(min=5, message ="Name Length should be more than 4")
 @Column(columnDefinition = "varchar(20) not null ")
 private String name;

// username : Cannot be null Length more than 4 must be unique
@NotEmpty(message = "User Name should not be Empty!")
@Size(min=5 , message ="Name Length should be more than 4")
@Column(columnDefinition = "varchar(30) not null unique")
private String userName;

// password : Cannot be null
@NotEmpty(message = "Password should not be Empty!")
@Column(columnDefinition = "varchar(50) not null")
private String password;

// email : Cannot be null must be valid
// email must be unique
 @Email
 @Column(columnDefinition = "varchar(50) not null unique ")
 private String email;

//role :Cannot be null must be user or admin only
@NotEmpty(message = "Role should not be Empty!")
@Pattern(regexp = "^(user|admin)$" , message = "Role has Two valid inputs only, user or admin!")
//@Column(columnDefinition = "varchar(20) not null check(role='user' or role='admin')")
private String role;

// age Cannot be null must be integer
@NotNull(message = "Age should not be Null!")
@Positive()
@Column(columnDefinition = "int not null")
private int age;

}
