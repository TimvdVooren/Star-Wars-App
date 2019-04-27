package com.example.timva.starwarsapp.Data;

import java.io.Serializable;
import java.util.List;

public class StarWarsSpecies implements Serializable {
    private String name;
    private String classification;
    private String designation;
    private String average_height;
    private String skin_colors;
    private String hair_colors;
    private String eye_colors;
    private String average_lifespan;
    private StarWarsPlanet homeworld;
    private String language;
    private List<String> people;
    private List<String> films;
}
