package com.example.timva.starwarsapp;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.timva.starwarsapp.Data.StarWarsCharacter;
import com.example.timva.starwarsapp.Views.DetailedActivity;
import com.example.timva.starwarsapp.Views.MainActivity;

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
        if (character.isFavourite())
            viewHolder.favouriteButton.setImageResource(R.drawable.star_filled);
        else
            viewHolder.favouriteButton.setImageResource(R.drawable.star_outlined);
    }

    @Override
    public int getItemCount() {
        return characters.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView characterName;
        TextView characterBirthyear;
        ImageButton favouriteButton;

        public ViewHolder(View itemView, final Context c) {
            super(itemView);

            characterName = itemView.findViewById(R.id.ListCharacterName);
            characterBirthyear = itemView.findViewById(R.id.ListBirthyear);
            favouriteButton = itemView.findViewById(R.id.ListFavouriteButton);

            favouriteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (getAdapterPosition() >= 0) {
                        StarWarsCharacter currentCharacter = characters.get(getAdapterPosition());
                        currentCharacter.toggleFavourite();
                        if (currentCharacter.isFavourite()) {
                            favouriteButton.setImageResource(R.drawable.star_filled);
                            MainActivity.addCharacterToFavourites(currentCharacter);
                        }
                        else {
                            favouriteButton.setImageResource(R.drawable.star_outlined);
                            MainActivity.removeCharacterFromFavourites(currentCharacter);
                        }
                        notifyDataSetChanged();
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
