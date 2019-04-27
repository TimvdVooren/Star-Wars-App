package com.example.timva.starwarsapp.Data;

import java.io.Serializable;
import java.util.ArrayList;
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
    public String homeworld;
    public List<String> films;
    public List<String> species;
    public List<String> vehicles;
    public List<String> starships;
    public String created;
    public String edited;
    public String url;

    public StarWarsPlanet homeworldPlanet;
    public List<StarWarsFilm> starWarsFilms = new ArrayList<>();
    public List<StarWarsSpecies> starWarsSpecies = new ArrayList<>();
    public List<StarWarsVehicle> starWarsVehicles = new ArrayList<>();
    public List<StarWarsStarship> starWarsStarships = new ArrayList<>();


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
