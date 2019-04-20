package com.example.timva.starwarsapp;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.timva.starwarsapp.Data.StarWarsCharacter;
import com.example.timva.starwarsapp.Views.DetailedActivity;

import java.util.List;

public class CustomRecyclerAdapter extends RecyclerView.Adapter<CustomRecyclerAdapter.ViewHolder> {
    private Context context;
    private List<StarWarsCharacter> characters;

    public CustomRecyclerAdapter(Context context, List<StarWarsCharacter> characters) {
        this.context = context;
        this.characters = characters;
    }

    @NonNull
    @Override
    public CustomRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.detailed_list_item, parent, false);

        return new ViewHolder(view, context);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomRecyclerAdapter.ViewHolder viewHolder, int i) {
        StarWarsCharacter character = characters.get(i);

        viewHolder.characterName.setText(character.getName());
        viewHolder.characterBirthyear.setText(character.getBirth_year());
    }

    @Override
    public int getItemCount() {
        return characters.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView characterName;
        TextView characterBirthyear;
        ImageButton favoriteButton;

        public ViewHolder(View itemView, final Context c) {
            super(itemView);

            characterName = itemView.findViewById(R.id.ListCharacterName);
            characterBirthyear = itemView.findViewById(R.id.ListBirthyear);
            favoriteButton = itemView.findViewById(R.id.ListFavoriteButton);
            favoriteButton.setColorFilter(R.color.colorPrimary);

            favoriteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(getAdapterPosition() >= 0){
                        StarWarsCharacter currentCharacter = characters.get(getAdapterPosition());
                        currentCharacter.toggleFavorite();
                        if(currentCharacter.isFavorite())
                            favoriteButton.setColorFilter(R.color.colorAccent);
                        else
                            favoriteButton.setColorFilter(R.color.colorPrimary);
                    }
                }
            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(getAdapterPosition() >= 0){
                        Intent detailIntent = new Intent(context, DetailedActivity.class);
                        detailIntent.putExtra("StarWarsCharacter", characters.get(getAdapterPosition()));
                        context.startActivity(detailIntent);
                    }
                }
            });
        }
    }
}
