package com.ensolvers.mynotepad.my_notepad.entity;

import com.ensolvers.mynotepad.my_notepad.dto.enums.UserState;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.math.BigInteger;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@DynamicInsert
@DynamicUpdate
@SequenceGenerator(name = "SEQ_USER", sequenceName = "SEQ_USER", initialValue = 1, allocationSize = 1)
@Entity
@Table(name = "user")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_USER")
    @Column(name = "id")
    private BigInteger id;

    @Column(name = "name", length = 50, nullable = false)
    private String name;

    @Column(name = "second_name", length = 50)
    private String secondName;

    @Column(name = "first_last_name", length = 50, nullable = false)
    private String firstLastName;

    @Column(name = "second_last_name", length = 50)
    private String secondLastName;

    @Column(name = "email", length = 50, nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "age", nullable = false)
    private Integer age;

    @Column(name = "identification_number", length = 20, nullable = false)
    private String identificationNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "state", length = 8, nullable = false)
    private UserState state;
}
