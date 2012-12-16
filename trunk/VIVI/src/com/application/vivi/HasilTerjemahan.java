package com.application.vivi;

import java.util.Locale;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.speech.tts.TextToSpeech;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class HasilTerjemahan extends Activity implements TextToSpeech.OnInitListener {
	
	protected static final int MY_DATA_CHECK_CODE = 0;
	
	private String bhsOrg, bhsDest, hasil, hasilTerjemahan;
	private TextToSpeech tts;
	private Button btnBicara;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hasil_terjemahan);
        
        Intent i = getIntent();
        bhsOrg = i.getStringExtra("bhsOrg");
        bhsDest = i.getStringExtra("bhsDest");
        hasil = i.getStringExtra("hasil");
        hasilTerjemahan = i.getStringExtra("hasilTerjemahan");
        
        TextView txtViewBhsOrg = (TextView) findViewById(R.id.txtViewBhsOrg2);
        TextView txtViewBhsDest = (TextView) findViewById(R.id.txtViewBhsDest2);
        TextView txtViewHasil = (TextView) findViewById(R.id.txtViewHasil);
        TextView txtViewHasilTerjemahan = (TextView) findViewById(R.id.txtViewHasilTerjemahan);
        
        txtViewBhsOrg.setText(bhsOrg);
        txtViewBhsDest.setText(bhsDest);
        txtViewHasil.setText(hasil);
        txtViewHasilTerjemahan.setText(hasilTerjemahan);
        
        btnBicara = (Button) findViewById(R.id.buttonBicara);
        
        btnBicara.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				if (hasilTerjemahan != null && hasilTerjemahan.length() > 0) {
					tts.speak(hasilTerjemahan, TextToSpeech.QUEUE_FLUSH, null);
				}
			}
		});
        
        Intent check = new Intent();
		check.setAction(TextToSpeech.Engine.ACTION_CHECK_TTS_DATA);
		startActivityForResult(check, MY_DATA_CHECK_CODE);
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_hasil_terjemahan, menu);
        return true;
    }
    
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	
		if (requestCode == MY_DATA_CHECK_CODE) {
			if (resultCode == TextToSpeech.Engine.CHECK_VOICE_DATA_PASS) {
				// success, create the TTS instance
				tts = new TextToSpeech(this, this);
			} 
			else {
				// missing data, install it
				Intent install = new Intent();
				install.setAction(TextToSpeech.Engine.ACTION_INSTALL_TTS_DATA);
				startActivity(install);
			}
		}
	}

    public void onInit(int status) {
    	
		if (status == TextToSpeech.SUCCESS) {
			
			Toast.makeText(getApplicationContext(), 
					"Text-to-Speech engine is initialized", Toast.LENGTH_SHORT).show();
			
			if (tts.isLanguageAvailable(Locale.US) == TextToSpeech.LANG_AVAILABLE) {
				tts.setLanguage(Locale.US);
			
				btnBicara.setEnabled(true);
			}
			else //if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED)
				Toast.makeText(getApplicationContext(), "This language is not supported.", Toast.LENGTH_SHORT).show();
		}
		else if (status == TextToSpeech.ERROR) {
			Toast.makeText(getApplicationContext(), 
					"Error occurred while initializing Text-to-Speech engine", Toast.LENGTH_SHORT).show();
		}
	}
    
    @Override
    public void onDestroy() {
    	// to shut it down
    	if (tts != null) {
    		tts.stop();
    		tts.shutdown();
    	}
    	super.onDestroy();
    }
}
