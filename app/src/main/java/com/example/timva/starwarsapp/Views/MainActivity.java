package com.example.timva.starwarsapp.Views;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import com.example.timva.starwarsapp.CustomRecyclerAdapter;
import com.example.timva.starwarsapp.Data.StarWarsCharacter;
import com.example.timva.starwarsapp.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

public class MainActivity extends AppCompatActivity {
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //View declaration
        sortButton = findViewById(R.id.MainSortButton);
        recyclerView = findViewById(R.id.MainRecyclerView);
        favouritesButton = findViewById(R.id.MainFavouritesButton);

        characterList = new ArrayList<>();
        //TODO load characters from API
        characterList.add(new StarWarsCharacter("Tim", "2000"));
        characterList.add(new StarWarsCharacter("Joep", "1998"));
        characterList.add(new StarWarsCharacter("Zoë", "1997"));
        Collections.sort(characterList);

        favourites = new ArrayList<>();

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerAdapter = new CustomRecyclerAdapter(this, characterList);
        recyclerView.setAdapter(recyclerAdapter);

        setListeners();
    }

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
                            return o1.getBirth_year().compareTo(o2.getBirth_year());
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
}
