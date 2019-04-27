package com.example.timva.starwarsapp.Services;

import com.example.timva.starwarsapp.Data.StarWarsCharacter;
import com.example.timva.starwarsapp.Data.StarWarsList;

public interface VolleyListener {
    public void onCharacterAvailable(StarWarsCharacter character);
    public void onCharactersLoaded();
    public void onCharacterError();
}
