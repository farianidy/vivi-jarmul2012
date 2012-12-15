package com.application.vivi;

import java.util.ArrayList;

import com.memetix.mst.language.Language;
import com.memetix.mst.translate.Translate;

import android.os.AsyncTask;
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

	protected static final int RESULT_SPEECH = 1;
	
	private String bhsOrg, bhsDest, hasil, hasilTerjemahan;
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
				Intent rekam = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);

				rekam.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, "en-US");

				try {
					startActivityForResult(rekam, RESULT_SPEECH);
				} catch (ActivityNotFoundException a) {
					Toast.makeText(getApplicationContext(),
							"Oops! Your device doesn't support Speech-to-Text", Toast.LENGTH_SHORT).show();
					
					//btnNext.setEnabled(false);
					hasil = "ini hanya percobaan";
					hasilTerjemahan = "this is just a test";
				}
			}
		});
        
        new TranslateAsync() {
        	
        }.execute();
        
//      new TranslateAsync() { 
//      	protected void onPostExecute(Boolean result) {
//      		txtViewHasilTerjemahan.setText(hasilTerjemahan);
//      	}
//  	}.execute();
        
        btnNext.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				Intent nextScreen = new Intent();
				nextScreen.setClass(getApplicationContext(), HasilTerjemahan.class);
				nextScreen.putExtra("bhsOrg", bhsOrg);
				nextScreen.putExtra("bhsDest", bhsDest);
				nextScreen.putExtra("hasil", hasil);
				nextScreen.putExtra("hasilTerjemahan", hasilTerjemahan);
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
				if (resultCode == RESULT_OK && data != null) {
					ArrayList<String> text = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
					hasil = text.get(0);
				}
				break;
			}
		}
	}
    
    class TranslateAsync extends AsyncTask<Void, Integer, Boolean> {
    	
        @Override
        protected Boolean doInBackground(Void... arg0) {
        	
        	Translate.setClientId("06091991");
            Translate.setClientSecret("ljuWhXc8GnMmr4yUswoPLYfnxp5ORsiUNFBu+73fWuI=");
            
            try {

            	if("Indonesian".equals(bhsDest))
            		hasilTerjemahan = Translate.execute(hasil, Language.ENGLISH, Language.INDONESIAN);
            	else if ("Arabic".equals(bhsDest))
            		hasilTerjemahan = Translate.execute(hasil, Language.ENGLISH, Language.ARABIC);
            	else if ("Danish".equals(bhsDest))
            		hasilTerjemahan = Translate.execute(hasil, Language.ENGLISH, Language.DANISH);
            	else if ("Dutch".equals(bhsDest))
            		hasilTerjemahan = Translate.execute(hasil, Language.ENGLISH, Language.DUTCH);
            	else if ("French".equals(bhsDest))
            		hasilTerjemahan = Translate.execute(hasil, Language.ENGLISH, Language.FRENCH);
            	else if ("German".equals(bhsDest))
            		hasilTerjemahan = Translate.execute(hasil, Language.ENGLISH, Language.GERMAN);
            	else if ("Italian".equals(bhsDest))
            		hasilTerjemahan = Translate.execute(hasil, Language.ENGLISH, Language.ITALIAN);
            	else if ("Japanese".equals(bhsDest))
            		hasilTerjemahan = Translate.execute(hasil, Language.ENGLISH, Language.JAPANESE);
            	else if ("Korean".equals(bhsDest))
            		hasilTerjemahan = Translate.execute(hasil, Language.ENGLISH, Language.KOREAN);
            	else if ("Polish".equals(bhsDest))
            		hasilTerjemahan = Translate.execute(hasil, Language.ENGLISH, Language.POLISH);
            	else if ("Russian".equals(bhsDest))
            		hasilTerjemahan = Translate.execute(hasil, Language.ENGLISH, Language.RUSSIAN);
            	else if ("Spanish".equals(bhsDest))
            		hasilTerjemahan = Translate.execute(hasil, Language.ENGLISH, Language.SPANISH);
            	else 
            		hasilTerjemahan = Translate.execute(hasil, Language.ENGLISH, Language.INDONESIAN);
            	
            } catch(Exception e) {
            	hasilTerjemahan = e.toString();
            }
            
            return true;
        }
    }
}
