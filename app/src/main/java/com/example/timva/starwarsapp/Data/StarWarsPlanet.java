package com.example.timva.starwarsapp.Data;

import java.util.List;

public class StarWarsPlanet {
    private String name;
    private String rotation_period;
    private String orbital_period;
    private String diameter;
    private String climate;
    private String arid;
    private String gravity;
    private String terrain;
    private String surface_water;
    private String population;
    private List<StarWarsCharacter> residents;
    private List<StarWarsFilm> films;

    public String getName() {
        return name;
    }
}
