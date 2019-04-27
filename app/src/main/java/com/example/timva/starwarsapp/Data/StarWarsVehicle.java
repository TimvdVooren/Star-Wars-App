package com.example.timva.starwarsapp.Data;

import java.io.Serializable;
import java.util.List;

public class StarWarsVehicle implements Serializable {
    private String name;
    private String model;
    private String manufacturer;
    private String cost_in_credits;
    private String length;
    private String max_atmosphering_speed;
    private String crew;
    private String passengers;
    private String cargo_capacity;
    private String consumables;
    private String vehicle_class;
    private List<String> pilots;
    private List<String> films;
}
