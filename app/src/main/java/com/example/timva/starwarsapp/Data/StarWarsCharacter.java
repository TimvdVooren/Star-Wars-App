package com.example.timva.starwarsapp.Data;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

public class StarWarsCharacter implements Comparable<StarWarsCharacter>, Serializable {

    public String name;
    public String height;
    public String mass;
    public String hair_color;
    public String skin_color;
    public String eye_color;
    public String birth_year;
    public String gender;

    @JsonProperty("homeworld")
    public String homeworldUrl;
    @JsonProperty("films")
    public List<String> filmUrls;
    @JsonProperty("species")
    public List<String> specieUrls;
    @JsonProperty("vehicles")
    public List<String> vehicleUrls;
    @JsonProperty("starships")
    public List<String> starShipUrls;

    public String created;
    public String edited;
    public String url;

    public boolean favourite = false;

    public void toggleFavourite(){
        favourite = !favourite;
    }

    public boolean isFavourite() {
        return favourite;
    }

    @Override
    public int compareTo(StarWarsCharacter otherCharacter) {
        return this.name.compareTo(otherCharacter.name);
    }
}
