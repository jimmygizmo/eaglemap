package ai.smartmetal.eaglemap.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String code;
//    private int population;  // Removing country population for now. Issue with source data.

    // Constructors
    public Country() {}

    public Country(String name,
                   String code)
//                   int population)
    {
        this.name = name;
        this.code = code;
//        this.population = population;
    }


    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

//    public int getPopulation() { return population; }
//    public void setPopulation(int population) { this.population = population; }
}  // class Country  (Jakarta/JPA model)


// NOTES:

// History of name change from "JPA - Java Persistence Architecture" to "Jakarta Persistence":
//
// The name Jakarta replaced JPA (Java Persistence API) in 2019 when Oracle transferred Java EE to the
// Eclipse Foundation, which then renamed it Jakarta EE. Due to trademark restrictions, the javax namespace
// could no longer be used, leading to the renaming of packages and properties from javax.persistence to
// jakarta.persistence. The official release with the new namespace, Jakarta EE 9, occurred on December 8, 2020,
// marking the complete transition of JPA to Jakarta Persistence.

