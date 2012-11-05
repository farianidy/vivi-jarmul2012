package com.application.vivi;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;

public class PilihBahasa extends Activity {
	
	private Spinner spinnerBhsOrg, spinnerBhsDest;
	private Button btnNext;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pilih_bahasa);
        
        spinnerBhsOrg = (Spinner) findViewById(R.id.spinnerBhsOrg);
        spinnerBhsDest = (Spinner) findViewById(R.id.spinnerBhsDest);
        btnNext = (Button) findViewById(R.id.buttonBhs);
        
        btnNext.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				Intent nextScreen = new Intent();
				nextScreen.setClass(getApplicationContext(), Input.class);
				nextScreen.putExtra("bhsOrg", String.valueOf(spinnerBhsOrg.getSelectedItem()));
				nextScreen.putExtra("bhsDest", String.valueOf(spinnerBhsDest.getSelectedItem()));
				startActivity(nextScreen);
			}
		});
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_pilih_bahasa, menu);
        return true;
    }
}