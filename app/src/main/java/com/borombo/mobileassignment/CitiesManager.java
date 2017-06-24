package com.borombo.mobileassignment;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.borombo.mobileassignment.model.City;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Borombo on 24/06/2017.
 */

public class CitiesManager {

    private static final CitiesManager instance = new CitiesManager();

    private static final String DATA_KEY = "Cities";

    private SharedPreferences preferences;
    private ArrayList<City> cities = new ArrayList<>();
    private Gson gson = new Gson();

    public static CitiesManager getInstance() {
        return instance;
    }

    private CitiesManager() {
    }

    public int getNumberOfCities(Context context){
        getList(context);
        int res = 0;
        if (cities != null)
            res = cities.size();
        return res;
    }

    private void getList(Context context){
        preferences = PreferenceManager
                .getDefaultSharedPreferences(context);
        String json = preferences.getString(DATA_KEY, "");
        Type type = new TypeToken<List<City>>(){}.getType();
        ArrayList<City> data = gson.fromJson(json, type);
        if(data != null){
             cities = data;
         }

    }

    public void add(City city){
        cities.add(city);
        SharedPreferences.Editor editor = preferences.edit();
        String json = gson.toJson(cities);
        editor.putString(DATA_KEY, json);
        editor.commit();
    }

    public City getById(int id){
        return cities.get(id);
    }


}
