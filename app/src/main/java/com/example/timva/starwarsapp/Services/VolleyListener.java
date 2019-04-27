package com.example.timva.starwarsapp.Services;

import com.example.timva.starwarsapp.Data.StarWarsCharacter;
import com.example.timva.starwarsapp.Data.StarWarsFilm;
import com.example.timva.starwarsapp.Data.StarWarsPlanet;
import com.example.timva.starwarsapp.Data.StarWarsStarship;
import com.example.timva.starwarsapp.Data.StarWarsVehicle;

public interface VolleyListener {
    public void onCharacterAvailable(StarWarsCharacter character);
    public void onCharactersLoaded();
    public void onPlanetAvailable(StarWarsCharacter character, StarWarsPlanet planet);
    public void onFilmAvailable(StarWarsCharacter character, StarWarsFilm film);
    public void onVehicleAvailable(StarWarsCharacter character, StarWarsVehicle vehicle);
    public void onStarshipAvailable(StarWarsCharacter character, StarWarsStarship starship);
    public void onApiError();
}
