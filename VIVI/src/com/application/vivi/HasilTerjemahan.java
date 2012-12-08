package com.application.vivi;


import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.widget.TextView;

import com.memetix.mst.language.Language;
import com.memetix.mst.translate.Translate;
import android.os.AsyncTask;

public class HasilTerjemahan extends Activity {
	
	private String bhsOrg, bhsDest, hasil;
	public String translatedText, hasil2;
	
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
        final TextView txtViewHasilTerjemahan = (TextView) findViewById(R.id.txtViewHasilTerjemahan);
        
        txtViewBhsOrg.setText(bhsOrg);
        txtViewBhsDest.setText(bhsDest);
        txtViewHasil.setText(hasil);
        new MyAsyncTask() { 
            protected void onPostExecute(Boolean result) {
            	txtViewHasilTerjemahan.setText(translatedText);
            }
        }.execute();

        
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_hasil_terjemahan, menu);
        return true;
    }
    
    class MyAsyncTask extends AsyncTask<Void, Integer, Boolean> {
        @Override
        protected Boolean doInBackground(Void... arg0) {
        	Translate.setClientId("06091991");
            Translate.setClientSecret("ljuWhXc8GnMmr4yUswoPLYfnxp5ORsiUNFBu+73fWuI=");
            try {

            	if("Indonesian".equals(bhsDest))
            		translatedText = Translate.execute(hasil, Language.ENGLISH, Language.INDONESIAN);
            	else if ("Arabic".equals(bhsDest))
            		translatedText = Translate.execute(hasil, Language.ENGLISH, Language.ARABIC);
            	else if ("Danish".equals(bhsDest))
                	translatedText = Translate.execute(hasil, Language.ENGLISH, Language.DANISH);
            	else if ("Dutch".equals(bhsDest))
                	translatedText = Translate.execute(hasil, Language.ENGLISH, Language.DUTCH);
            	else if ("French".equals(bhsDest))
                	translatedText = Translate.execute(hasil, Language.ENGLISH, Language.FRENCH);
            	else if ("German".equals(bhsDest))
                	translatedText = Translate.execute(hasil, Language.ENGLISH, Language.GERMAN);
            	else if ("Italian".equals(bhsDest))
                	translatedText = Translate.execute(hasil, Language.ENGLISH, Language.ITALIAN);
            	else if ("Japanese".equals(bhsDest))
                	translatedText = Translate.execute(hasil, Language.ENGLISH, Language.JAPANESE);
            	else if ("Korean".equals(bhsDest))
                	translatedText = Translate.execute(hasil, Language.ENGLISH, Language.KOREAN);
            	else if ("Polish".equals(bhsDest))
                	translatedText = Translate.execute(hasil, Language.ENGLISH, Language.POLISH);
            	else if ("Russian".equals(bhsDest))
                	translatedText = Translate.execute(hasil, Language.ENGLISH, Language.RUSSIAN);
            	else if ("Spanish".equals(bhsDest))
                	translatedText = Translate.execute(hasil, Language.ENGLISH, Language.SPANISH);
            	else 
                	translatedText = Translate.execute(hasil, Language.ENGLISH, Language.INDONESIAN);
            	
            } catch(Exception e) {
            	translatedText = e.toString();
            }
            return true;
        }	
    }
}
