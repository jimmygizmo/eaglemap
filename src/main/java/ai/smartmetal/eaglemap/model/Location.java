package ai.smartmetal.eaglemap.model;

import jakarta.persistence.*;

@Entity
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String asciiname;
    private double latitude;
    private double longitude;
    private String country;
    private double population;
    private int elevation;

    // Constructors
    public Location() {}

    public Location(String name,
                    String asciiname,
                    double latitude,
                    double longitude,
                    String country,
                    double population,
                    int elevation)
    {
        this.name = name;
        this.asciiname = asciiname;
        this.latitude = latitude;
        this.longitude = longitude;
        this.country = country;
        this.population = population;
        this.elevation = elevation;
    }


    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getAsciiname() { return asciiname; }
    public void setAsciiname(String asciiname) { this.asciiname = asciiname; }

    public double getLatitude() { return latitude; }
    public void setLatitude(double latitude) { this.latitude = latitude; }

    public double getLongitude() { return longitude; }
    public void setLongitude(double longitude) { this.longitude = longitude; }

    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }

    public double getPopulation() { return population; }
    public void setPopulation(double population) { this.population = population; }

    public int getElevation() { return elevation; }
    public void setElevation(int elevation) { this.elevation = elevation; }
}  // class Location  (Jakarta/JPA model)


// NOTES:

// History of name change from "JPA - Java Persistence Architecture" to "Jakarta Persistence":
//
// The name Jakarta replaced JPA (Java Persistence API) in 2019 when Oracle transferred Java EE to the
// Eclipse Foundation, which then renamed it Jakarta EE. Due to trademark restrictions, the javax namespace
// could no longer be used, leading to the renaming of packages and properties from javax.persistence to
// jakarta.persistence. The official release with the new namespace, Jakarta EE 9, occurred on December 8, 2020,
// marking the complete transition of JPA to Jakarta Persistence.

