package com.example.timva.starwarsapp.Views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.timva.starwarsapp.Data.StarWarsFilm;
import com.example.timva.starwarsapp.Data.StarWarsPlanet;
import com.example.timva.starwarsapp.Data.StarWarsStarship;
import com.example.timva.starwarsapp.Data.StarWarsVehicle;
import com.example.timva.starwarsapp.Services.CustomRecyclerAdapter;
import com.example.timva.starwarsapp.Data.StarWarsCharacter;
import com.example.timva.starwarsapp.R;
import com.example.timva.starwarsapp.Services.VolleyConnection;
import com.example.timva.starwarsapp.Services.VolleyListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

public class MainActivity extends AppCompatActivity implements VolleyListener {
    //Views
    private Button sortButton;
    private RecyclerView recyclerView;
    private Button favouritesButton;

    //Variables
    private ArrayList<StarWarsCharacter> characterList;
    private static ArrayList<StarWarsCharacter> favourites;
    private CustomRecyclerAdapter recyclerAdapter;
    private boolean sortByName = true;
    private boolean favouritesPressed = false;
    private VolleyConnection connection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //View declaration
        sortButton = findViewById(R.id.MainSortButton);
        recyclerView = findViewById(R.id.MainRecyclerView);
        favouritesButton = findViewById(R.id.MainFavouritesButton);

        characterList = new ArrayList<>();
        favourites = new ArrayList<>();

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerAdapter = new CustomRecyclerAdapter(this, characterList);
        recyclerView.setAdapter(recyclerAdapter);

        connection = VolleyConnection.getInstance(getApplicationContext(), this);
        connection.getCharacters();

        //getCharactersFromApi();

        setListeners();
    }

//    int characterAmount = 0;
//    private void getCharactersFromApi(){
//        Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
//                .baseUrl("https://swapi.co/api/")
//                .addConverterFactory(GsonConverterFactory.create());
//        Retrofit retrofit = retrofitBuilder.build();
//
//        RetrofitClient client = retrofit.create(RetrofitClient.class);
//
//        //Get the character list
//        Call<StarWarsList<StarWarsCharacter>> listCall = client.getCharacterList();
//        listCall.enqueue(new Callback<StarWarsList<StarWarsCharacter>>() {
//            @Override
//            public void onResponse(Call<StarWarsList<StarWarsCharacter>> call, Response<StarWarsList<StarWarsCharacter>> response) {
//                StarWarsList<StarWarsCharacter> starWarsList = response.body();
//                characterAmount = starWarsList.count;
//                Log.d("API LIST CALL", characterAmount + "");
//            }
//
//            @Override
//            public void onFailure(Call<StarWarsList<StarWarsCharacter>> call, Throwable t) {
//                Toast.makeText(getApplicationContext(), "Error: something went wrong with the API", Toast.LENGTH_LONG);
//            }
//        });
//
//        //Get all the characters and deserialize them
//        for(int i = 0; i < characterAmount; i++){
//            Call<StarWarsCharacter> characterCall = client.getCharacter(i);
//            characterCall.enqueue(new Callback<StarWarsCharacter>() {
//                @Override
//                public void onResponse(Call<StarWarsCharacter> call, Response<StarWarsCharacter> response) {
//                    StarWarsCharacter character = response.body();
//                    characterList.add(character);
//                    Log.d("API CHARACTER CALL", character + "");
//                }
//
//                @Override
//                public void onFailure(Call<StarWarsCharacter> call, Throwable t) {
//                    Toast.makeText(getApplicationContext(), "Error: something went wrong with the API", Toast.LENGTH_LONG);
//                }
//            });
//        }
//    }

    public static void addCharacterToFavourites(StarWarsCharacter character){
        favourites.add(character);
    }

    public static void removeCharacterFromFavourites(StarWarsCharacter character){
        Iterator<StarWarsCharacter> itr = favourites.iterator();
        while(itr.hasNext())
            if(itr.next().equals(character))
                itr.remove();
    }

    private void setListeners() {
        sortButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!sortByName){
                    Collections.sort(characterList);
                    Collections.sort(favourites);

                    recyclerAdapter.notifyDataSetChanged();
                    sortButton.setText(getString(R.string.sort_style_name));
                    sortByName = true;
                }
                else{
                    Comparator<StarWarsCharacter> characterComparator = new Comparator<StarWarsCharacter>() {
                        @Override
                        public int compare(StarWarsCharacter o1, StarWarsCharacter o2) {
                            if(o1.birth_year.equals("unknown"))
                                return 1;
                            if(o2.birth_year.equals("unknown"))
                                return -1;

                            double birthYear1 = Double.parseDouble(o1.birth_year.substring(0, o1.birth_year.length()-3));
                            double birthYear2 = Double.parseDouble(o2.birth_year.substring(0, o2.birth_year.length()-3));

                            return (int)(birthYear2 - birthYear1);
                        }
                    };

                    Collections.sort(characterList, characterComparator);
                    Collections.sort(favourites, characterComparator);

                    recyclerAdapter.notifyDataSetChanged();
                    sortButton.setText(getString(R.string.sort_style_birthyear));
                    sortByName = false;
                }
            }
        });

        favouritesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(favouritesPressed) {
                    recyclerAdapter = new CustomRecyclerAdapter(getApplicationContext(), favourites);
                    favouritesButton.setText(getString(R.string.normal_list));
                }
                else {
                    recyclerAdapter = new CustomRecyclerAdapter(getApplicationContext(), characterList);
                    favouritesButton.setText(getString(R.string.favourites));
                }

                recyclerView.setAdapter(recyclerAdapter);
                favouritesPressed = !favouritesPressed;
            }
        });
    }

    @Override
    public void onCharacterAvailable(StarWarsCharacter character) {
        characterList.add(character);
    }

    @Override
    public void onCharactersLoaded() {
        Collections.sort(characterList);
        recyclerAdapter.notifyDataSetChanged();
        for(StarWarsCharacter character : characterList) {
            connection.getPlanetFromCharacter(character);
            connection.getFilmsFromCharacter(character);
            connection.getVehiclesFromCharacter(character);
        }

        sortButton.setEnabled(true);
        favouritesButton.setEnabled(true);
    }

    @Override
    public void onPlanetAvailable(StarWarsCharacter character, StarWarsPlanet planet) {
        character.homeworldPlanet = planet;
    }

    @Override
    public void onFilmAvailable(StarWarsCharacter character, StarWarsFilm film) {
        character.starWarsFilms.add(film);
    }

    @Override
    public void onVehicleAvailable(StarWarsCharacter character, StarWarsVehicle vehicle) {
        character.starWarsVehicles.add(vehicle);
    }

    @Override
    public void onStarshipAvailable(StarWarsCharacter character, StarWarsStarship starship) {
        character.starWarsStarships.add(starship);
    }

    @Override
    public void onApiError() {
        Toast.makeText(getApplicationContext(), "Error: something went wrong with the API", Toast.LENGTH_LONG);
    }
}
