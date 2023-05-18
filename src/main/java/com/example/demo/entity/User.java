package com.example.demo.entity;

import com.example.demo.validators.UserValidator.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Table(name = "user")
@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NotEmpty(groups = {CaseByMail.class, CaseByBank.class, CaseByGosuslugi.class})
    String name;

    @NotEmpty(groups = {CaseByBank.class, CaseByGosuslugi.class})
    String lastName;

    @NotEmpty(groups = {CaseByBank.class, CaseByGosuslugi.class})
    String surname;

    @NotNull(groups = {CaseByBank.class, CaseByGosuslugi.class})
    Long bankId;

    @NotNull(groups = {CaseByBank.class, CaseByGosuslugi.class})
    LocalDate birthDate;

    @NotEmpty(groups = CaseByGosuslugi.class)
    String birthCity;

    @NotEmpty(groups = {CaseByBank.class, CaseByGosuslugi.class})
    @Column(length = 11)
    String passportNumber;

    @Pattern(regexp = "7\\d{10}")
    @NotEmpty(groups = {CaseByMobile.class, CaseByGosuslugi.class})
    @Column(length = 11)
    String phoneNumber;

    @Email(regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$", groups = CaseByMail.class)
    String email;

    String registeredAddress;

    String residenceAddress;

    @CreationTimestamp
    LocalDateTime creationTime;
}
