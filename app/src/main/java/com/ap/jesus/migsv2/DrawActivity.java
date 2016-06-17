package com.ap.jesus.migsv2;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.StrictMode;
import graph.Label;
import graph.Pair;
import java.util.ArrayList;
import java.util.List;

public class DrawActivity extends Activity {
    GUIGraph guiGraph;
    double[] values;
    double delta;
    private String[] categories = {"historia", "espana", "fdi", "docencia", "videojuegos", "cine", "curiosidad", "ciencias", "perifericos", "arte", "redes", "pcs", "hardware", "almacenamiento", "servidores"};

    @Override
    public void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        guiGraph = new GUIGraph(this,null , 90);
        values = new double[categories.length];
        Intent intent = getIntent();
        for (int i = 0; i < categories.length; i++) {
            values[i] = intent.getDoubleExtra(categories[i], 0);
        }
        delta = intent.getDoubleExtra("delta", 0);
        List<Pair<Label, Double>> userweights = new ArrayList<Pair<Label,Double>>();
        Pair<Label, Double> p;
        for(int i = 0; i < categories.length; i++){
            p = new Pair<Label, Double>(Label.valueOf(categories[i]), values[i]);
            userweights.add(p);
        }
        guiGraph = new GUIGraph(DrawActivity.this, userweights, delta);

        //drawGrid.setBackgroundColor(Color.WHITE);
        guiGraph.setBackgroundColor(Color.WHITE);
//        setContentView(drawGrid);
        setContentView( guiGraph);

    }

    
}
