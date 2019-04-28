package com.example.timva.starwarsapp.Services;

import com.example.timva.starwarsapp.Data.StarWarsCharacter;
import com.example.timva.starwarsapp.Data.StarWarsFilm;
import com.example.timva.starwarsapp.Data.StarWarsPlanet;
import com.example.timva.starwarsapp.Data.StarWarsStarShip;
import com.example.timva.starwarsapp.Data.StarWarsVehicle;

public interface VolleyListener {
    //Is called upon when a character is returned from the API
    public void onCharacterAvailable(StarWarsCharacter character);
    //Is called upon when all characters have been returned from the API
    public void onCharactersLoaded();
    //Is called upon when a planet is returned from the API
    public void onPlanetAvailable(StarWarsCharacter character, StarWarsPlanet planet);
    //Is called upon when a film is returned from the API
    public void onFilmAvailable(StarWarsCharacter character, StarWarsFilm film);
    //Is called upon when a vehicle is returned from the API
    public void onVehicleAvailable(StarWarsCharacter character, StarWarsVehicle vehicle);
    //Is called upon when a starship is returned from the API
    public void onStarshipAvailable(StarWarsCharacter character, StarWarsStarShip starship);
    //Is called upon when an error occurs while interacting with the API
    public void onApiError();
}
