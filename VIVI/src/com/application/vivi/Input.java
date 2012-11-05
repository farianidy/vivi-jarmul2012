package com.application.vivi;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Input extends Activity {

	private String bhsOrg, bhsDest, kata;
	private Button btnRekam, btnNext;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);
        
        Intent i = getIntent();
        bhsOrg = i.getStringExtra("bhsOrg");
        bhsDest = i.getStringExtra("bhsDest");
        
        TextView txtViewBhsOrg = (TextView) findViewById(R.id.txtViewBhsOrg);
        TextView txtViewBhsDest = (TextView) findViewById(R.id.txtViewBhsDest);
        
        txtViewBhsOrg.setText(bhsOrg);
        txtViewBhsDest.setText(bhsDest);
        
        btnRekam = (Button) findViewById(R.id.buttonRekam);
        btnNext = (Button) findViewById(R.id.buttonLanjutHasil);
        
        btnRekam.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO: rekam
				kata = "tes";
			}
		});
        
        btnNext.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				Intent nextScreen = new Intent();
				nextScreen.setClass(getApplicationContext(), HasilTerjemahan.class);
				nextScreen.putExtra("bhsOrg", bhsOrg);
				nextScreen.putExtra("bhsDest", bhsDest);
				nextScreen.putExtra("kata", kata);
				startActivity(nextScreen);
			}
		});
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_input, menu);
        return true;
    }
}
