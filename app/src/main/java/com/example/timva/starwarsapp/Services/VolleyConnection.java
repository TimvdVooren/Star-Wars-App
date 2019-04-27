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
                            Log.i("GET CHARACTER", "OK");
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
                            volleyListener.onCharacterError();
                        }
                    }
            );
            this.queue.add(request);
        }
    }
}
