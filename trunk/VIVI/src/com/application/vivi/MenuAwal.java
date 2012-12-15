package com.application.vivi;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class MenuAwal extends Activity {
	
	private Button btnMulai, btnPetunjuk, btnKredit;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_awal);
        
        btnMulai = (Button) findViewById(R.id.buttonMulai);
        btnPetunjuk = (Button) findViewById(R.id.buttonPetunjuk);
        btnKredit = (Button) findViewById(R.id.buttonKredit);
        
        btnMulai.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				Intent nextScreen = new Intent();
				nextScreen.setClass(getApplicationContext(), PilihBahasa.class);
				startActivity(nextScreen);
			}
		});
        
        btnPetunjuk.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
		});
        
        btnKredit.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
		});
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_menu_awal, menu);
        return true;
    }
}
