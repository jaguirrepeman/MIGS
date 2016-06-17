package com.ap.jesus.migsv2;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import graph.Label;
import graph.Pair;
import java.util.ArrayList;
import java.util.List;

public class ConfirmedChoices extends AppCompatActivity {
    GUIGraph guiGraph;
    private String []inter;
    private int [] punt;
    int[] values;
    private String[] categories = {"historia", "espana", "fdi", "docencia", "videojuegos", "cine", "curiosidad", "ciencias", "perifericos", "arte", "redes", "pcs", "hardware", "almacenamiento", "servidores"};
    double fdival = 0;

    public void showChoices(){
     //   TextView tv = (TextView)findViewById(R.id.fdi_val);
      //  tv.setText(Double.toString(fdival));
        for (int i = 0; i < categories.length; i++){
            String str = categories[i];
            String aux = "value_";
            int textId = getResources().getIdentifier(aux + str, "id", getPackageName());
            TextView tv = (TextView)findViewById(textId);
            tv.setText(Integer.toString(values[i]));
        }


    }


    public void gotoDraw(){
        Button b = (Button)findViewById(R.id.draw_button);
        b.getBackground().setColorFilter(ContextCompat.getColor(getApplicationContext(), R.color.primaryColor), PorterDuff.Mode.MULTIPLY);
        b.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                        StrictMode.setThreadPolicy(policy);
                        List<Pair<Label, Double>> userweights = new ArrayList<Pair<Label,Double>>();
                        Pair<Label, Double> p;
                        for(int i = 0; i < categories.length; i++){
                            p = new Pair<Label, Double>(Label.valueOf(categories[i]), doubleVal(values[i]));
                            userweights.add(p);
                        }
                        EditText et = (EditText)findViewById(R.id.delta);
                        Intent intent = new Intent(getApplicationContext(), DrawActivity.class);
                        for (int i = 0; i < categories.length; i++) {
                            intent.putExtra(categories[i], doubleVal(values[i]));
                        }
                        intent.putExtra("delta",  Double.parseDouble(et.getText().toString()));
                        startActivityForResult(intent, 0);
                       /* guiGraph = new GUIGraph(ConfirmedChoices.this, userweights, Integer.parseInt(et.getText().toString()));

                        //drawGrid.setBackgroundColor(Color.WHITE);
                        guiGraph.setBackgroundColor(Color.WHITE);
                        setContentView(guiGraph);*/

                    }
                }
        );

    }
    public double doubleVal(int i){
        return ((double)i)/100;
    }
    public void goBack(){
        Button b = (Button)findViewById(R.id.back_button);
        b.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onBackPressed();
                        //Intent myIntent = new Intent(getApplicationContext(), Home.class);
                        //startActivityForResult(myIntent, 0);
                        //startActivityForResult(myIntent, 0);
                    }
                }
        );

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmed_choices);
        values = new int[categories.length];
        Intent intent = getIntent();
        for (int i = 0; i < categories.length; i++) {
            values[i] = intent.getIntExtra(categories[i], 0);
        }
        showChoices();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        gotoDraw();
        goBack();
        EditText et = (EditText)findViewById(R.id.delta);
        et.setText("90");
    }

}
