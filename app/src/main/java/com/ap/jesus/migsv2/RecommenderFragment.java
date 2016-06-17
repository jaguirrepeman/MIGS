package com.ap.jesus.migsv2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Toast;

import java.util.List;

public class RecommenderFragment extends Fragment {


        private static final String ARG_SECTION_NUMBER = "section_number";

        private String[] categories = {"historia", "espana", "fdi", "docencia", "videojuegos", "cine", "curiosidad", "ciencias", "perifericos", "arte", "redes", "pcs", "hardware", "almacenamiento", "servidores"};
        private int [] intvalues = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
        private static final String TAG = "UserPreferences";
        public int categoryIndex(String cat){
            for (int i = 0; i< categories.length; i++){
                String cata = categories[i];
                if (cata.compareTo(cat) == 0){ return i;}
            }
            return 1;
        }
        public static String upperName(String cat){
            if (cat.compareTo("historia") == 0) return "Historia";
            else if (cat.compareTo("espana") == 0) return "España";
            else if (cat.compareTo("fdi") == 0) return "FDI";
            else if (cat.compareTo("docencia") == 0) return "Docencia";
            else if (cat.compareTo("videojuegos") == 0) return "Videojuegos";
            else if (cat.compareTo("cine") == 0) return "Cine";
            else if (cat.compareTo("curiosidad") == 0) return "Curiosidades";
            else if (cat.compareTo("ciencias") == 0) return "Ciencias";
            else if (cat.compareTo("perifericos") == 0) return "Periféricos";
            else if (cat.compareTo("arte") == 0) return "Arte";
            else if (cat.compareTo("redes") == 0) return "Redes";
            else if (cat.compareTo("pcs") == 0) return "PCs";
            else if (cat.compareTo("hardware") == 0) return "Hardware";
            else if (cat.compareTo("almacenamiento") == 0) return "Almacenamiento";
            else if (cat.compareTo("servidores") == 0) return "Servidores";
            else return "Desconocido";
        }

        public void initializeIntvalues(){
            intvalues = new int[categories.length];
            for (int i = 0; i< categories.length; i++){
                intvalues[i] = 0;
            }
        }
        public void listCheckBox(View v){
            for (int i = 0; i < categories.length; i++) {
                String str = categories[i];
                String aux = "checkBox_";
                int textId = getResources().getIdentifier(aux + str, "id", MainActivity.PACKAGE_NAME);
                final CheckBox cb = (CheckBox) v.findViewById(textId);
                cb.setOnCheckedChangeListener(
                        new CompoundButton.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                                if (isChecked) {
                                    String strr = cb.getText().toString().toLowerCase();
                                    if (strr.compareTo("españa") == 0) strr = "espana";
                                    else if (strr.compareTo("curiosidades") == 0)
                                        strr = "curiosidad";
                                    else if (strr.compareTo("periféricos") == 0)
                                        strr = "perifericos";
                                    String aux = "seekBar_";
                                    int textId = getResources().getIdentifier(aux + strr, "id", MainActivity.PACKAGE_NAME);
                                    SeekBar sb = (SeekBar) getView().findViewById(textId);
                                    sb.setTag(strr);
                                    sb.setVisibility(View.VISIBLE);
                                }
                                if (!isChecked) {
                                    String strr = cb.getText().toString().toLowerCase();
                                    if (strr.compareTo("españa") == 0) strr = "espana";
                                    else if (strr.compareTo("curiosidades") == 0)
                                        strr = "curiosidad";
                                    else if (strr.compareTo("periféricos") == 0)
                                        strr = "perifericos";
                                    String aux = "seekBar_";
                                    int textId = getResources().getIdentifier(aux + strr, "id", MainActivity.PACKAGE_NAME);
                                    SeekBar sb = (SeekBar) getView().findViewById(textId);
                                    sb.setTag(strr);
                                    sb.setVisibility(View.INVISIBLE);
                                }
                            }
                        }
                );
            }
        }

        public void seekBar(View v){
            for (int i = 0; i < categories.length; i++) {
                String str = categories[i];
                String aux = "seekBar_";
                int textId = getResources().getIdentifier(aux + str, "id", MainActivity.PACKAGE_NAME);
                final SeekBar sb = (SeekBar) v.findViewById(textId);
                sb.setOnSeekBarChangeListener(
                        new SeekBar.OnSeekBarChangeListener() {

                            @Override
                            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                                intvalues[categoryIndex(sb.getTag().toString())] = progress;

                            }

                            @Override
                            public void onStartTrackingTouch(SeekBar seekBar) {

                            }

                            @Override
                            public void onStopTrackingTouch(SeekBar seekBar) {
                                if (intvalues[categoryIndex(sb.getTag().toString())] < 100) {
                                    Toast.makeText(getActivity(), upperName(sb.getTag().toString()) + ": 0." + intvalues[categoryIndex(sb.getTag().toString())], Toast.LENGTH_SHORT).show();
                                }else
                                    Toast.makeText(getActivity(), upperName(sb.getTag().toString()) + ": 1", Toast.LENGTH_SHORT).show();
                            }
                        }
                );
            }
        }

        public void openConfirmedChoices(View v){
            Button b = (Button)v.findViewById(R.id.button_confirmar);
            b.getBackground().setColorFilter(ContextCompat.getColor(getContext(), R.color.primaryColor), PorterDuff.Mode.MULTIPLY);
            b.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getActivity(), ConfirmedChoices.class);
                            for (int i = 0; i < categories.length; i++) {
                                intent.putExtra(categories[i], (intvalues[i]));
                            }
                            //   intent.putExtra("secondKeyName","SecondKeyValue");
                            startActivity(intent);
                        }
                    }
            );

        }

        public void resetChoices(View v){
            Button b = (Button)v.findViewById(R.id.button_back);
            b.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            for (int i = 0; i < categories.length; i++) {
                                String str = categories[i];
                                String skstr = "seekBar_";
                                String cbstr = "checkBox_";

                                int textId = getResources().getIdentifier(skstr + str, "id", MainActivity.PACKAGE_NAME);
                                SeekBar sb = (SeekBar) getActivity().findViewById(textId);
                                sb.setProgress(0);
                                textId = getResources().getIdentifier(cbstr + str, "id", MainActivity.PACKAGE_NAME);
                                CheckBox cb = (CheckBox) getActivity().findViewById(textId);
                                cb.setChecked(false);
                            }
                        }
                    }
            );

        }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.content_home, container, false);
        initializeIntvalues();
        listCheckBox(v);
        openConfirmedChoices(v);
        seekBar(v);
        resetChoices(v);
        return v;
    }

    /**
     * Creación prefabricada de un {@link RecommenderFragment}
     */
    public static RecommenderFragment newInstance(int sectionNumber) {
        RecommenderFragment fragment = new RecommenderFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public RecommenderFragment() {
    }


    @Override
    public void onResume(){
        super.onResume();
    }
    public void configureTab(){

    }
}