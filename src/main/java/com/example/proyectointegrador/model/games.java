package com.example.proyectointegrador.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "games")
public class games {

    @Id
    private String id;
    private String developers;
    private String launchDate;
    private String name;

    public games(){

    }

    public games(String id, String Developers, String launchDate, String name) {
        this.id = id;
        this.developers = Developers;
        this.launchDate = launchDate;
        this.name = name;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDevelopers() {
        return developers;
    }

    public void setDevelopers(String Developers) {
        this.developers = Developers;
    }

    public String getLaunchDate() {
        return launchDate;
    }

    public void setLaunchDate(String launchDate) {
        this.launchDate = launchDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
