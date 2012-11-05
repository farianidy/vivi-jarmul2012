package com.application.vivi;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.widget.TextView;

public class HasilTerjemahan extends Activity {
	
	private String bhsOrg, bhsDest, kata;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hasil_terjemahan);
        
        Intent i = getIntent();
        bhsOrg = i.getStringExtra("bhsOrg");
        bhsDest = i.getStringExtra("bhsDest");
        kata = i.getStringExtra("kata");
        
        TextView txtViewBhsOrg = (TextView) findViewById(R.id.txtViewBhsOrg2);
        TextView txtViewBhsDest = (TextView) findViewById(R.id.txtViewBhsDest2);
        TextView txtViewKata = (TextView) findViewById(R.id.txtViewKata);
        
        txtViewBhsOrg.setText(bhsOrg);
        txtViewBhsDest.setText(bhsDest);
        txtViewKata.setText(kata);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_hasil_terjemahan, menu);
        return true;
    }
}
