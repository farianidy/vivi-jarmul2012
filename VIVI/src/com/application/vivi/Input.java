package com.application.vivi;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Input extends Activity {

	private String bhsOrg, bhsDest, hasil;
	private Button btnRekam, btnNext;
	protected static final int RESULT_SPEECH = 1;
	
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
				Intent intent = new Intent(
						RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
				
				if (bhsOrg.equals("Chinese Simplified"))
					intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, "zh-CN");
				else if (bhsOrg.equals("Chinese Traditional"))
					intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, "zh-TW");
				else if (bhsOrg.equals("English"))
					intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, "en-US");
				else if (bhsOrg.equals("French"))
					intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, "fr");
				else if (bhsOrg.equals("German"))
					intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, "de");
				else if (bhsOrg.equals("Indonesian"))
					intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, "id");
				else if (bhsOrg.equals("Italian"))
					intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, "it");
				else if (bhsOrg.equals("Japanese"))
					intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, "ja");
				else if (bhsOrg.equals("Korean"))
					intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, "ko");
				else
					intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);

				try {
					startActivityForResult(intent, RESULT_SPEECH);
				} catch (ActivityNotFoundException a) {
					Toast t = Toast.makeText(getApplicationContext(),
							"Ops! Your device doesn't support Speech to Text",
							Toast.LENGTH_SHORT);
					t.show();
					btnNext.setEnabled(false);
				}
			}
		});
        
        btnNext.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				Intent nextScreen = new Intent();
				nextScreen.setClass(getApplicationContext(), Hasil.class);
				nextScreen.putExtra("bhsOrg", bhsOrg);
				nextScreen.putExtra("bhsDest", bhsDest);
				nextScreen.putExtra("hasil", hasil);
				startActivity(nextScreen);
			}
		});
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_input, menu);
        return true;
    }
    
    @Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		switch (requestCode) {
			case RESULT_SPEECH: {
				if (resultCode == RESULT_OK && null != data) {
					ArrayList<String> text = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
					hasil = text.get(0);
				}
				else
					btnNext.setEnabled(false);
				break;
			}
		}
	}
}
