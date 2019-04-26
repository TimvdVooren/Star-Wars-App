package com.example.timva.starwarsapp.Data;

import java.io.Serializable;
import java.util.ArrayList;

public class StarWarsCharacter implements Comparable<StarWarsCharacter>, Serializable {
    private String name;
    private String height;
    private String mass;
    private String hair_color;
    private String skin_color;
    private String eye_color;
    private String birth_year;
    private String gender;
    private StarWarsPlanet homeworld;
    private ArrayList<StarWarsFilm> films;
    private ArrayList<StarWarsSpecies> species;
    private ArrayList<StarWarsVehicle> vehicles;
    private ArrayList<StarWarsStarShip> star_ships;

    private boolean favourite = false;

    public StarWarsCharacter(String name, String height, String mass, String hair_color, String skin_color, String eye_color, String birth_year, String gender, ArrayList<StarWarsFilm> films, ArrayList<StarWarsSpecies> species, ArrayList<StarWarsVehicle> vehicles, ArrayList<StarWarsStarShip> star_ships) {
        this.name = name;
        this.height = height;
        this.mass = mass;
        this.hair_color = hair_color;
        this.skin_color = skin_color;
        this.eye_color = eye_color;
        this.birth_year = birth_year;
        this.gender = gender;
        this.films = films;
        this.species = species;
        this.vehicles = vehicles;
        this.star_ships = star_ships;
    }

    public StarWarsCharacter(String name, String birth_year) {
        this.name = name;
        this.birth_year = birth_year;
    }

    public void toggleFavourite(){
        favourite = !favourite;
    }

    public String getName() {
        return name;
    }

    public String getHeight() {
        return height;
    }

    public String getMass() {
        return mass;
    }

    public String getHair_color() {
        return hair_color;
    }

    public String getSkin_color() {
        return skin_color;
    }

    public String getEye_color() {
        return eye_color;
    }

    public String getBirth_year() {
        return birth_year;
    }

    public String getGender() {
        return gender;
    }

    public StarWarsPlanet getHomeworld() {
        return homeworld;
    }

    public ArrayList<StarWarsFilm> getFilms() {
        return films;
    }

    public ArrayList<StarWarsSpecies> getSpecies() {
        return species;
    }

    public ArrayList<StarWarsVehicle> getVehicles() {
        return vehicles;
    }

    public ArrayList<StarWarsStarShip> getStar_ships() {
        return star_ships;
    }

    public boolean isFavourite() {
        return favourite;
    }

    @Override
    public int compareTo(StarWarsCharacter otherCharacter) {
        return this.getName().compareTo(otherCharacter.getName());
    }
}
