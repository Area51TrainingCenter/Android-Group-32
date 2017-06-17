package com.jcodee.temapokemon.rest.response;

import java.util.ArrayList;

/**
 * Created by johannfjs on 17/06/17.
 * Email: johann.jara@jcodee.com
 * Phone: (+51) 990870011
 */

public class GeneracionResponse {
    private String name;
    private int id;
    private ArrayList<EspecieResponse> pokemon_species;

    public ArrayList<EspecieResponse> getPokemon_species() {
        return pokemon_species;
    }

    public void setPokemon_species(ArrayList<EspecieResponse> pokemon_species) {
        this.pokemon_species = pokemon_species;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
