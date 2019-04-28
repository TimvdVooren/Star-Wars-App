package com.example.timva.starwarsapp.Views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.timva.starwarsapp.Data.StarWarsCharacter;
import com.example.timva.starwarsapp.Data.StarWarsFilm;
import com.example.timva.starwarsapp.Data.StarWarsStarShip;
import com.example.timva.starwarsapp.Data.StarWarsVehicle;
import com.example.timva.starwarsapp.R;

import java.util.ArrayList;

public class DetailedActivity extends AppCompatActivity {
    //Views
    private TextView characterName;
    private TextView characterBirthyear;
    private TextView characterHomeworld;
    private ListView filmList;
    private ListView vehicleList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed);

        //View declaration
        characterName = findViewById(R.id.DetailedCharacterName);
        characterBirthyear = findViewById(R.id.DetailedBirthyear);
        characterHomeworld = findViewById(R.id.DetailedHomeworld);
        filmList = findViewById(R.id.DetailedFilmList);
        vehicleList = findViewById(R.id.DetailedVehicleList);

        Intent characterIntent = getIntent();
        StarWarsCharacter character = (StarWarsCharacter) characterIntent.getSerializableExtra("StarWarsCharacter");

        characterName.setText(character.name);
        characterBirthyear.setText(getString(R.string.birthyear) + " " + character.birth_year);
        characterHomeworld.setText(getString(R.string.homeworld) + " " + character.homeworldPlanet.name);

        ArrayList<String> filmTitles = new ArrayList<>();
        for(StarWarsFilm film : character.starWarsFilms)
            filmTitles.add(film.title);
        filmList.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, filmTitles));

        ArrayList<String> vehicleNames = new ArrayList<>();
        for(StarWarsVehicle vehicle : character.starWarsVehicles)
            vehicleNames.add(vehicle.name);
        for(StarWarsStarShip starship : character.starWarsStarShips)
            vehicleNames.add(starship.name);
        vehicleList.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, vehicleNames));
    }
}
