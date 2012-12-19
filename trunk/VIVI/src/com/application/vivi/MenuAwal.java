package com.application.vivi;

import android.os.Bundle;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class MenuAwal extends Activity {
	
	private Button btnBahasa, btnNext;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_awal);
        
        btnBahasa = (Button) findViewById(R.id.buttonAturBahasa);
        btnNext = (Button) findViewById(R.id.buttonNext);
        
        btnBahasa.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				Intent in = new Intent(Intent.ACTION_MAIN);
				in.setComponent(new ComponentName("com.google.android.voicesearch", "com.google.android.voicesearch.VoiceSearchPreferences"));
				in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				startActivity(in);
				
			}
		});
        
        btnNext.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				Intent nextScreen = new Intent();
				nextScreen.setClass(getApplicationContext(), PilihBahasa.class);
				startActivity(nextScreen);
			}
		});
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_menu_awal, menu);
        return true;
    }
}
