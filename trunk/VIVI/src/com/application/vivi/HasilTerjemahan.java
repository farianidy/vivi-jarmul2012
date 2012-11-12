package com.application.vivi;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.widget.TextView;

public class HasilTerjemahan extends Activity {
	
	private String bhsOrg, bhsDest, hasil;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hasil_terjemahan);
        
        Intent i = getIntent();
        bhsOrg = i.getStringExtra("bhsOrg");
        bhsDest = i.getStringExtra("bhsDest");
        hasil = i.getStringExtra("hasil");
        
        TextView txtViewBhsOrg = (TextView) findViewById(R.id.txtViewBhsOrg2);
        TextView txtViewBhsDest = (TextView) findViewById(R.id.txtViewBhsDest2);
        TextView txtViewHasil = (TextView) findViewById(R.id.txtViewHasil);
        TextView txtViewHasilTerjemahan = (TextView) findViewById(R.id.txtViewHasilTerjemahan);
        
        txtViewBhsOrg.setText(bhsOrg);
        txtViewBhsDest.setText(bhsDest);
        txtViewHasil.setText(hasil);
        txtViewHasilTerjemahan.setText("");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_hasil_terjemahan, menu);
        return true;
    }
}
