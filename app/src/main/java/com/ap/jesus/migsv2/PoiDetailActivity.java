package com.ap.jesus.migsv2;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;


public class PoiDetailActivity extends Activity {

    public static final String EXTRAS_KEY_POI_ID = "id";
    public static final String EXTRAS_KEY_POI_TITILE = "title";
    public static final String EXTRAS_KEY_POI_DESCR = "description";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.migs_poi_detail);

        ((TextView)findViewById(R.id.poi_id)).setText(  getIntent().getExtras().getString("id") );
        ((TextView)findViewById(R.id.poi_title)).setText( getIntent().getExtras().getString("title") );
        ((TextView)findViewById(R.id.poi_description)).setText(  getIntent().getExtras().getString("description") );
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // app icon in action bar clicked; go home
                super.onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
