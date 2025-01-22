package com.election.electionbackend.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String password;
    private LocalDate birthDate;
    private String city;
    private String province;
    private String role;
    private String supporter;
    private String firstName;
    private String lastName;
    private String social1;
    private String social2;
    private String social3;
    private String social4;
    private String profileDescription;

    // Constructor met 3 argumenten (id, name, email)
    public User(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    // Standaard constructor
    public User() {}

    // Nieuwe method om de details van de gebruiker bij te werken
    public void updateDetails(User updatedUser) {
        if (updatedUser.getName() != null) {
            this.name = updatedUser.getName();
        }
        if (updatedUser.getEmail() != null) {
            this.email = updatedUser.getEmail();
        }
        if (updatedUser.getPassword() != null) {
            this.password = updatedUser.getPassword();
        }
        if (updatedUser.getBirthDate() != null) {
            this.birthDate = updatedUser.getBirthDate();
        }
        if (updatedUser.getCity() != null) {
            this.city = updatedUser.getCity();
        }
        if (updatedUser.getProvince() != null) {
            this.province = updatedUser.getProvince();
        }
        if (updatedUser.getRole() != null) {
            this.role = updatedUser.getRole();
        }
        if (updatedUser.getSupporter() != null) {
            this.supporter = updatedUser.getSupporter();
        }
        if (updatedUser.getFirstName() != null) {
            this.firstName = updatedUser.getFirstName();
        }
        if (updatedUser.getLastName() != null) {
            this.lastName = updatedUser.getLastName();
        }
        if (updatedUser.getSocial1() != null) {
            this.social1 = updatedUser.getSocial1();
        }
        if (updatedUser.getSocial2() != null) {
            this.social2 = updatedUser.getSocial2();
        }
        if (updatedUser.getSocial3() != null) {
            this.social3 = updatedUser.getSocial3();
        }
        if (updatedUser.getSocial4() != null) {
            this.social4 = updatedUser.getSocial4();
        }
        if (updatedUser.getProfileDescription() != null) {
            this.profileDescription = updatedUser.getProfileDescription();
        }
    }

    // Getters en Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getSupporter() {
        return supporter;
    }

    public void setSupporter(String supporter) {
        this.supporter = supporter;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSocial1() {
        return social1;
    }

    public void setSocial1(String social1) {
        this.social1 = social1;
    }

    public String getSocial2() {
        return social2;
    }

    public void setSocial2(String social2) {
        this.social2 = social2;
    }

    public String getSocial3() {
        return social3;
    }

    public void setSocial3(String social3) {
        this.social3 = social3;
    }

    public String getSocial4() {
        return social4;
    }

    public void setSocial4(String social4) {
        this.social4 = social4;
    }

    public String getProfileDescription() {
        return profileDescription;
    }

    public void setProfileDescription(String profileDescription) {
        this.profileDescription = profileDescription;
    }
}
