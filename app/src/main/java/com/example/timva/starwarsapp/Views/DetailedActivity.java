package com.example.timva.starwarsapp.Views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.timva.starwarsapp.Data.StarWarsCharacter;
import com.example.timva.starwarsapp.R;

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
        characterName.setText(character.getName());
        characterBirthyear.setText(getString(R.string.birthyear) + character.getBirth_year());
        //characterHomeworld.setText(getString(R.string.homeworld) + character.getHomeworld().getName());
        //filmList.setAdapter(new ArrayAdapter<String>(this, 0, character.getFilms()));
        //vehicleList.setAdapter(new ArrayAdapter<String>(this, 0, character.getVehicles());
    }
}
