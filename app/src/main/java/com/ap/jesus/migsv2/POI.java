package com.ap.jesus.migsv2;

import org.json.JSONArray;

import java.util.Arrays;
import java.util.List;
import graph.Label;
import graph.Pair;

public class POI {
    public POI(String n){
        name = n;
    }
    public POI() {
    }

    private String name;
    private int image;
    private List<Pair<Label, Double>> userweights;
    private String url;
    private String description;
    private int time;

    public POI(String name, int image, String description, List<Pair<Label, Double>> userweights, int time, String url) {
        this.name = name;
        this.image = image;
        this.description = description;
        this.userweights = userweights;
        this.url = url;
        this.time = time;
    }

    public String toString(){
        return name;
    }

    public String getName() {
        return name;
    }

    public int getImage() {
        return image;
    }

    public String getPreferences() {
        String str = "", aux;
        if (userweights.size() > 1) {
            for (int i = 0; i < userweights.size() - 1; i++) {
                aux = userweights.get(i).getFirstElement().toString();
                str += RecommenderFragment.upperName(aux) + ", ";
            }
        }
        aux = userweights.get(userweights.size() - 1).getFirstElement().toString();
        str += RecommenderFragment.upperName(aux);
        return str;
    }
    public String getFormattedPreferences() {
        String str = "", aux;
        if (userweights.size() > 1) {
            for (int i = 0; i < userweights.size() - 1; i++) {
                aux = userweights.get(i).getFirstElement().toString();


                str += "\t" + RecommenderFragment.upperName(aux) + "\n";
            }
        }
        aux = userweights.get(userweights.size() - 1).getFirstElement().toString();
        str += "\t" + RecommenderFragment.upperName(aux);
        return str;
    }
    public int gettime() {
        return time;
    }


    public String getDescription(){
        return description;
    }
    public String getUrl(){
        return url;
    }


}
