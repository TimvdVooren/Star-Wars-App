package com.example.timva.starwarsapp.Data;

import java.io.Serializable;
import java.util.List;

public class StarWarsFilm implements Serializable {
    public String title;
    public int episode_id;
    public String opening_crawl;
    public String director;
    public String producer;
    public String release_date;
    public List<String> characters;
    public List<String> planets;
    public List<String> species;
    public List<String> vehicles;
    public List<String> star_ships;
}
