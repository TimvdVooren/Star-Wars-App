package com.example.timva.starwarsapp.Data;

import java.io.Serializable;
import java.util.List;

public class StarWarsPlanet implements Serializable {
    public String name;
    public String rotation_period;
    public String orbital_period;
    public String diameter;
    public String climate;
    public String arid;
    public String gravity;
    public String terrain;
    public String surface_water;
    public String population;
    public List<String> residents;
    public List<String> films;
}
