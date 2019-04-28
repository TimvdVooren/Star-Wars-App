package com.example.timva.starwarsapp.Services;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.timva.starwarsapp.Data.StarWarsCharacter;
import com.example.timva.starwarsapp.Data.StarWarsFilm;
import com.example.timva.starwarsapp.Data.StarWarsPlanet;
import com.example.timva.starwarsapp.Data.StarWarsStarship;
import com.example.timva.starwarsapp.Data.StarWarsVehicle;
import com.google.gson.Gson;

import org.json.JSONObject;

public class VolleyConnection {
    private static VolleyConnection instance = null;
    private Context context;
    private RequestQueue queue;
    private VolleyListener volleyListener;

    private VolleyConnection(Context context, VolleyListener listener){
        this.context = context;
        queue = Volley.newRequestQueue(context);
        this.volleyListener = listener;
    }

    public static VolleyConnection getInstance(Context context, VolleyListener listener){
        if(instance == null)
            instance = new VolleyConnection(context, listener);
        return instance;
    }

    //Gets all of the StarWarsCharacters from the API
    public void getCharacters(){
        String requestUrl;
        JsonObjectRequest request;

        for(int i = 1; i < 88; i++){
            final int index = i;
            requestUrl = "https://swapi.co/api/people/" + i;
            request = new JsonObjectRequest(
                    Request.Method.GET,
                    requestUrl,
                    null,

                    new Response.Listener<JSONObject>(){

                        @Override
                        public void onResponse(JSONObject response) {
                            //Log.i("GET CHARACTER", "OK");
                            Gson gson = new Gson();

                            StarWarsCharacter character = gson.fromJson(response.toString(), StarWarsCharacter.class);
                            volleyListener.onCharacterAvailable(character);
                            if(index == 87){
                                volleyListener.onCharactersLoaded();
                                Log.i("DONE LOADING CHARACTERS", "OK");
                            }
                        }
                    },

                    new Response.ErrorListener(){

                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.e("GET CHARACTER", "NOK");
                            volleyListener.onApiError();
                        }
                    }
            );
            this.queue.add(request);
        }
    }

    //Gets the StarWarsPlanets associated with the given StarWarsCharacter from the API
    public void getPlanetFromCharacter(final StarWarsCharacter character){
        String planetUrl = character.homeworld;
        JsonObjectRequest request;

        request = new JsonObjectRequest(
                Request.Method.GET,
                planetUrl,
                null,

                new Response.Listener<JSONObject>(){

                    @Override
                    public void onResponse(JSONObject response) {
                        //Log.i("GET PLANET", "OK");
                        Gson gson = new Gson();

                        StarWarsPlanet planet = gson.fromJson(response.toString(), StarWarsPlanet.class);
                        volleyListener.onPlanetAvailable(character, planet);
                    }
                },

                new Response.ErrorListener(){

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("GET PLANET", "NOK");
                        volleyListener.onApiError();
                    }
                }
        );
        this.queue.add(request);
    }

    //Gets the StarWarsFilms associated with the given StarWarsCharacter from the API
    public void getFilmsFromCharacter(final StarWarsCharacter character){
        JsonObjectRequest request;

        for(String filmUrl : character.films){
            request = new JsonObjectRequest(
                    Request.Method.GET,
                    filmUrl,
                    null,

                    new Response.Listener<JSONObject>(){

                        @Override
                        public void onResponse(JSONObject response) {
                            //Log.i("GET FILM", "OK");
                            Gson gson = new Gson();

                            StarWarsFilm film = gson.fromJson(response.toString(), StarWarsFilm.class);
                            volleyListener.onFilmAvailable(character, film);
                        }
                    },

                    new Response.ErrorListener(){

                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.e("GET FILM", "NOK");
                            volleyListener.onApiError();
                        }
                    }
            );
            this.queue.add(request);
        }
    }

    //Gets the StarWarsVehicles associated with the given StarWarsCharacter from the API
    public void getVehiclesFromCharacter(final StarWarsCharacter character){
        JsonObjectRequest request;

        for(final String vehicleUrl : character.vehicles){
            request = new JsonObjectRequest(
                    Request.Method.GET,
                    vehicleUrl,
                    null,

                    new Response.Listener<JSONObject>(){

                        @Override
                        public void onResponse(JSONObject response) {
                            //Log.i("GET VEHICLE", "OK");
                            Gson gson = new Gson();

                            StarWarsVehicle vehicle = gson.fromJson(response.toString(), StarWarsVehicle.class);
                            volleyListener.onVehicleAvailable(character, vehicle);
                        }
                    },

                    new Response.ErrorListener(){

                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.e("GET VEHICLE", "NOK");
                            volleyListener.onApiError();
                        }
                    }
            );
            this.queue.add(request);
        }
    }

    //Gets the StarWarsStarships associated with the given StarWarsCharacter from the API
    public void getStarshipsFromCharacter(final StarWarsCharacter character){
        JsonObjectRequest request;

        for(final String starshipUrl : character.starships){
            request = new JsonObjectRequest(
                    Request.Method.GET,
                    starshipUrl,
                    null,

                    new Response.Listener<JSONObject>(){

                        @Override
                        public void onResponse(JSONObject response) {
                            //Log.i("GET STARSHIP", "OK");
                            Gson gson = new Gson();

                            StarWarsStarship starship = gson.fromJson(response.toString(), StarWarsStarship.class);
                            volleyListener.onStarshipAvailable(character, starship);
                        }
                    },

                    new Response.ErrorListener(){

                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.e("GET STARSHIP", "NOK");
                            volleyListener.onApiError();
                        }
                    }
            );
            this.queue.add(request);
        }
    }
}
