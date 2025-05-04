package ai.smartmetal.eaglemap.model;

import jakarta.persistence.*;

@Entity
public class AppUser {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;

    // TODO: Getters and setters

}
