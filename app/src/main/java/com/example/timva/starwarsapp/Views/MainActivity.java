package com.example.timva.starwarsapp.Views;

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

public class MainActivity extends AppCompatActivity {
    //Views
    private Button sortButton;
    private RecyclerView recyclerView;

    //Variables
    private ArrayList<StarWarsCharacter> characterList;
    private CustomRecyclerAdapter recyclerAdapter;
    private boolean sortByName = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        characterList = new ArrayList<>();
        characterList.add(new StarWarsCharacter("Tim", "2000"));
        characterList.add(new StarWarsCharacter("Joep", "1998"));
        characterList.add(new StarWarsCharacter("Zoë", "1997"));

        Collections.sort(characterList);

        //View declaration
        sortButton = findViewById(R.id.MainSortButton);
        recyclerView = findViewById(R.id.MainRecycler);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerAdapter = new CustomRecyclerAdapter(this, characterList);
        recyclerView.setAdapter(recyclerAdapter);

        setListeners();
    }

    private void setListeners() {
        sortButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!sortByName){
                    Collections.sort(characterList);
                    recyclerAdapter.notifyDataSetChanged();
                    sortButton.setText(getString(R.string.sort_style_name));
                    sortByName = true;
                }
                else{
                    Collections.sort(characterList, new Comparator<StarWarsCharacter>() {
                        @Override
                        public int compare(StarWarsCharacter o1, StarWarsCharacter o2) {
                            return o1.getName().compareTo(o2.getName());
                        }
                    });
                    recyclerAdapter.notifyDataSetChanged();
                    sortButton.setText(getString(R.string.sort_style_birthyear));
                    sortByName = false;
                }
            }
        });
    }
}
