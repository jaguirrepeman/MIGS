package com.ap.jesus.migsv2;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class POIDetail extends AppCompatActivity {


    private static final String EXTRA_NAME = "com.pad.pad_planificador.name";
    private static final String EXTRA_DRAWABLE = "com.pad.pad_planificador.drawable";

    /**
     * Inicia una nueva instancia de la actividad
     *
     * @param activity Contexto desde donde se lanzará
     * @param title    Item a procesar
     */
    public static void createInstance(Activity activity, POI title) {
        Intent intent = getLaunchIntent(activity, title);
        activity.startActivity(intent);
    }

    /**
     * Construye un Intent a partir del contexto y la actividad
     * de detalle.
     *
     * @param context Contexto donde se inicia
     * @param POI    Identificador del equipo
     * @return Intent listo para usar
     */
    public static Intent getLaunchIntent(Context context, POI POI) {
        Intent intent = new Intent(context, POIDetail.class);
        intent.putExtra(EXTRA_NAME, POI.getName());
        intent.putExtra(EXTRA_DRAWABLE, POI.getImage());
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.migs_poi_detail);

        if (getSupportActionBar() != null) // Habilitar up button
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent i = getIntent();
//        String name = i.getStringExtra(EXTRA_NAME);
//        int idDrawable = i.getIntExtra(EXTRA_DRAWABLE, -1);

        int id = i.getIntExtra("id", 0);

        int idDrawable = POIs.getPOIs()[id].getImage();
        int time = POIs.getPOIs()[id].gettime();
        String description = POIs.getPOIs()[id].getDescription();
        String weights = POIs.getPOIs()[id].getFormattedPreferences();
        String name = POIs.getPOIs()[id].getName();
        setTitle(name);
        String url = POIs.getPOIs()[id].getUrl();
        TextView nameContent = (TextView) findViewById(R.id.poi_detail_info_name_content);
        nameContent.setText("\t" + name);
        TextView urlContent = (TextView) findViewById(R.id.poi_detail_info_url_content);
        urlContent.setText(url);
        TextView descriptionContent = (TextView) findViewById(R.id.poi_detail_info_description_content);
        descriptionContent.setText(description);
        TextView weightsContent = (TextView) findViewById(R.id.poi_detail_info_weights_content);
        weightsContent.setText(weights);
        TextView timeContent = (TextView) findViewById(R.id.poi_detail_info_time_content);
        String timeString = "\t";
        if (time == 1){
            timeString += time + " minuto";
            timeContent.setText(timeString);
        }else{
            timeString += time + " minutos";
            timeContent.setText(timeString);
        }


        CollapsingToolbarLayout collapser =
                (CollapsingToolbarLayout) findViewById(R.id.collapser);
        collapser.setTitle(name); // Cambiar título

        loadImageParallax(idDrawable);// Cargar Imagen

    }


    private void loadImageParallax(int id) {
        ImageView image = (ImageView) findViewById(R.id.image_paralax);
        // Usando Glide para la carga asíncrona
        Glide.with(this).load(id).centerCrop().into(image);
    }


}
