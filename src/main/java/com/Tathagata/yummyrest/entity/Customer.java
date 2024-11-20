package com.Tathagata.yummyrest.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import lombok.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank(message = "First name is mandatory")
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name")
    private String lastName;
    @Email(message = "Email should be valid")
    @NotBlank(message = "Email is mandatory")
    @Column(name = "email", nullable = false, unique = true)
    private String email;
    @NotBlank(message = "Password is mandatory")
    @Column(name="password", nullable = false)
    private String password;
    public void setPassword(String password) {
        this.password = new BCryptPasswordEncoder().encode(password);
    }
}
