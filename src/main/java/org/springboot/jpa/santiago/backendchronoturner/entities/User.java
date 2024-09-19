package org.springboot.jpa.santiago.backendchronoturner.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springboot.jpa.santiago.backendchronoturner.utils.enumUser.UserType;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
        //Atributos de User
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;
    private String email;
    private String password;
    private LocalDateTime registrationDate;
    private LocalDateTime lastAccessDate;
    private UserType userType;
    @OneToMany(mappedBy = "user")
    private List<Task> taskList;
    @OneToMany(mappedBy = "user")
    private List<Goal> goalList;

    //Constructores de User
    //Asignadores de atributos de User (setters)
    //Lectores de atributos de User (getters)
    //Métodos de User
}
