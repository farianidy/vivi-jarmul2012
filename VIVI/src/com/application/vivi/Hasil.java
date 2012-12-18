package com.application.vivi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Locale;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.memetix.mst.language.Language;
import com.memetix.mst.translate.Translate;

import com.application.vivi.adapters.GoogleImageBean;
import com.application.vivi.adapters.ListViewImageAdapter;

public class Hasil extends Activity implements TextToSpeech.OnInitListener {
	
	protected static final int MY_DATA_CHECK_CODE = 0;
	
	private String bhsOrg, bhsDest, hasil, hasilTerjemahan, kataKunci = null;
	private ArrayList<Object> listImages;
	private Activity activity;

	private ListView listViewImages;
	private ListViewImageAdapter adapter;
	private TextView txtViewHasilTerjemahan;
	private TextToSpeech tts;
	private Button btnBicara;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		Intent i = getIntent();
		bhsOrg = i.getStringExtra("bhsOrg");
		bhsDest = i.getStringExtra("bhsDest");
		hasil = i.getStringExtra("hasil");

		TextView txtViewBhsOrg = (TextView) findViewById(R.id.txtViewBhsOrg2);
		TextView txtViewBhsDest = (TextView) findViewById(R.id.txtViewBhsDest2);
		TextView txtViewHasil = (TextView) findViewById(R.id.txtViewHasil);
		txtViewHasilTerjemahan = (TextView) findViewById(R.id.txtViewHasilTerjemahan);

		txtViewBhsOrg.setText(bhsOrg);
		txtViewBhsDest.setText(bhsDest);
		txtViewHasil.setText(hasil);
		txtViewHasilTerjemahan.setText("Your translated text here...");

		activity = this;
		listViewImages = (ListView) findViewById(R.id.lviewImages);

		if (hasil != null && hasil.length() > 0) {
			kataKunci = Uri.encode(hasil);
			System.out.println("Search string => " +  kataKunci);
			
			new getImagesTask().execute();
		}

		btnBicara = (Button) findViewById(R.id.buttonBicara);
		btnBicara.setEnabled(false);
		
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

	public class getImagesTask extends AsyncTask<Void, Void, Void>
	{
		JSONObject json;
		ProgressDialog dialog;

		@Override
		protected void onPreExecute() {
			super.onPreExecute();

			dialog = ProgressDialog.show(Hasil.this, "", "Please wait...");
		}

		@Override
		protected Void doInBackground(Void... params) {
			URL url;
			try {
				url = new URL("https://ajax.googleapis.com/ajax/services/search/images?" +
						"v=1.0&q="+ kataKunci +"&rsz=8"); //&key=ABQIAAAADxhJjHRvoeM2WF3nxP5rCBRcGWwHZ9XQzXD3SWg04vbBlJ3EWxR0b0NVPhZ4xmhQVm3uUBvvRF-VAA&userip=192.168.0.172");

				URLConnection connection = url.openConnection();
				connection.addRequestProperty("Referer", "http://technotalkative.com");

				String line;
				StringBuilder builder = new StringBuilder();
				BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

				while((line = reader.readLine()) != null) {
					builder.append(line);
				}
				System.out.println("Builder string => " + builder.toString());
				json = new JSONObject(builder.toString());
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (JSONException e) {
				e.printStackTrace();
			}

			Translate.setClientId("06091991");
			Translate.setClientSecret("ljuWhXc8GnMmr4yUswoPLYfnxp5ORsiUNFBu+73fWuI=");

			try {
				if (bhsOrg.equals("Chinese Simplified")) {
					if ("Chinese Traditional".equals(bhsDest))
						hasilTerjemahan = Translate.execute(hasil, Language.CHINESE_SIMPLIFIED, Language.CHINESE_TRADITIONAL);
					else if ("English".equals(bhsDest))
						hasilTerjemahan = Translate.execute(hasil, Language.CHINESE_SIMPLIFIED, Language.ENGLISH);
					else if ("French".equals(bhsDest))
						hasilTerjemahan = Translate.execute(hasil, Language.CHINESE_SIMPLIFIED, Language.FRENCH);
					else if ("German".equals(bhsDest))
						hasilTerjemahan = Translate.execute(hasil, Language.CHINESE_SIMPLIFIED, Language.GERMAN);
					else if ("Italian".equals(bhsDest))
						hasilTerjemahan = Translate.execute(hasil, Language.CHINESE_SIMPLIFIED, Language.ITALIAN);
					else if ("Japanese".equals(bhsDest))
						hasilTerjemahan = Translate.execute(hasil, Language.CHINESE_SIMPLIFIED, Language.JAPANESE);
					else if ("Korean".equals(bhsDest))
						hasilTerjemahan = Translate.execute(hasil, Language.CHINESE_SIMPLIFIED, Language.KOREAN);
				}
				else if (bhsOrg.equals("Chinese Traditional")) {
					if ("Chinese Simplified".equals(bhsDest))
						hasilTerjemahan = Translate.execute(hasil, Language.CHINESE_TRADITIONAL, Language.CHINESE_SIMPLIFIED);
					else if ("English".equals(bhsDest))
						hasilTerjemahan = Translate.execute(hasil, Language.CHINESE_TRADITIONAL, Language.ENGLISH);
					else if ("French".equals(bhsDest))
						hasilTerjemahan = Translate.execute(hasil, Language.CHINESE_TRADITIONAL, Language.FRENCH);
					else if ("German".equals(bhsDest))
						hasilTerjemahan = Translate.execute(hasil, Language.CHINESE_TRADITIONAL, Language.GERMAN);
					else if ("Italian".equals(bhsDest))
						hasilTerjemahan = Translate.execute(hasil, Language.CHINESE_TRADITIONAL, Language.ITALIAN);
					else if ("Japanese".equals(bhsDest))
						hasilTerjemahan = Translate.execute(hasil, Language.CHINESE_TRADITIONAL, Language.JAPANESE);
					else if ("Korean".equals(bhsDest))
						hasilTerjemahan = Translate.execute(hasil, Language.CHINESE_TRADITIONAL, Language.KOREAN);
				}
				else if (bhsOrg.equals("English")) {
					if ("Chinese Simplified".equals(bhsDest))
						hasilTerjemahan = Translate.execute(hasil, Language.ENGLISH, Language.CHINESE_SIMPLIFIED);
					else if ("Chinese Traditional".equals(bhsDest))
						hasilTerjemahan = Translate.execute(hasil, Language.ENGLISH, Language.CHINESE_TRADITIONAL);
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
				}
				else if (bhsOrg.equals("French")) {
					if ("Chinese Simplified".equals(bhsDest))
						hasilTerjemahan = Translate.execute(hasil, Language.ENGLISH, Language.CHINESE_SIMPLIFIED);
					else if ("Chinese Traditional".equals(bhsDest))
						hasilTerjemahan = Translate.execute(hasil, Language.ENGLISH, Language.CHINESE_TRADITIONAL);
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
				}
				else if (bhsOrg.equals("German")) {
					if ("Chinese Simplified".equals(bhsDest))
						hasilTerjemahan = Translate.execute(hasil, Language.ENGLISH, Language.CHINESE_SIMPLIFIED);
					else if ("Chinese Traditional".equals(bhsDest))
						hasilTerjemahan = Translate.execute(hasil, Language.ENGLISH, Language.CHINESE_TRADITIONAL);
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
				}
				else if (bhsOrg.equals("Indonesian")) {
					if ("Chinese Simplified".equals(bhsDest))
						hasilTerjemahan = Translate.execute(hasil, Language.INDONESIAN, Language.CHINESE_SIMPLIFIED);
					else if ("Chinese Traditional".equals(bhsDest))
						hasilTerjemahan = Translate.execute(hasil, Language.INDONESIAN, Language.CHINESE_TRADITIONAL);
					else if ("English".equals(bhsDest))
						hasilTerjemahan = Translate.execute(hasil, Language.INDONESIAN, Language.ENGLISH);
					else if ("French".equals(bhsDest))
						hasilTerjemahan = Translate.execute(hasil, Language.INDONESIAN, Language.FRENCH);
					else if ("German".equals(bhsDest))
						hasilTerjemahan = Translate.execute(hasil, Language.INDONESIAN, Language.GERMAN);
					else if ("Italian".equals(bhsDest))
						hasilTerjemahan = Translate.execute(hasil, Language.INDONESIAN, Language.ITALIAN);
					else if ("Japanese".equals(bhsDest))
						hasilTerjemahan = Translate.execute(hasil, Language.INDONESIAN, Language.JAPANESE);
					else if ("Korean".equals(bhsDest))
						hasilTerjemahan = Translate.execute(hasil, Language.INDONESIAN, Language.KOREAN);
				}
				else if (bhsOrg.equals("Italian")) {
					if ("Chinese Simplified".equals(bhsDest))
						hasilTerjemahan = Translate.execute(hasil, Language.ITALIAN, Language.CHINESE_SIMPLIFIED);
					else if ("Chinese Traditional".equals(bhsDest))
						hasilTerjemahan = Translate.execute(hasil, Language.ITALIAN, Language.CHINESE_TRADITIONAL);
					else if ("English".equals(bhsDest))
						hasilTerjemahan = Translate.execute(hasil, Language.ITALIAN, Language.ENGLISH);
					else if ("French".equals(bhsDest))
						hasilTerjemahan = Translate.execute(hasil, Language.ITALIAN, Language.FRENCH);
					else if ("German".equals(bhsDest))
						hasilTerjemahan = Translate.execute(hasil, Language.ITALIAN, Language.GERMAN);
					else if ("Japanese".equals(bhsDest))
						hasilTerjemahan = Translate.execute(hasil, Language.ITALIAN, Language.JAPANESE);
					else if ("Korean".equals(bhsDest))
						hasilTerjemahan = Translate.execute(hasil, Language.ITALIAN, Language.KOREAN);
				}
				else if (bhsOrg.equals("Japanese")) {
					if ("Chinese Simplified".equals(bhsDest))
						hasilTerjemahan = Translate.execute(hasil, Language.JAPANESE, Language.CHINESE_SIMPLIFIED);
					else if ("Chinese Traditional".equals(bhsDest))
						hasilTerjemahan = Translate.execute(hasil, Language.JAPANESE, Language.CHINESE_TRADITIONAL);
					else if ("English".equals(bhsDest))
						hasilTerjemahan = Translate.execute(hasil, Language.JAPANESE, Language.ENGLISH);
					else if ("French".equals(bhsDest))
						hasilTerjemahan = Translate.execute(hasil, Language.JAPANESE, Language.FRENCH);
					else if ("German".equals(bhsDest))
						hasilTerjemahan = Translate.execute(hasil, Language.JAPANESE, Language.GERMAN);
					else if ("Italian".equals(bhsDest))
						hasilTerjemahan = Translate.execute(hasil, Language.JAPANESE, Language.ITALIAN);
					else if ("Korean".equals(bhsDest))
						hasilTerjemahan = Translate.execute(hasil, Language.JAPANESE, Language.KOREAN);
				}
				else if (bhsOrg.equals("Korean")) {
					if ("Chinese Simplified".equals(bhsDest))
						hasilTerjemahan = Translate.execute(hasil, Language.KOREAN, Language.CHINESE_SIMPLIFIED);
					else if ("Chinese Traditional".equals(bhsDest))
						hasilTerjemahan = Translate.execute(hasil, Language.KOREAN, Language.CHINESE_TRADITIONAL);
					else if ("English".equals(bhsDest))
						hasilTerjemahan = Translate.execute(hasil, Language.KOREAN, Language.ENGLISH);
					else if ("French".equals(bhsDest))
						hasilTerjemahan = Translate.execute(hasil, Language.KOREAN, Language.FRENCH);
					else if ("German".equals(bhsDest))
						hasilTerjemahan = Translate.execute(hasil, Language.KOREAN, Language.GERMAN);
					else if ("Italian".equals(bhsDest))
						hasilTerjemahan = Translate.execute(hasil, Language.KOREAN, Language.ITALIAN);
					else if ("Japanese".equals(bhsDest))
						hasilTerjemahan = Translate.execute(hasil, Language.KOREAN, Language.JAPANESE);
				}
			} catch(Exception e) {
				hasilTerjemahan = e.toString();
			}

			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);

			if(dialog.isShowing())
				dialog.dismiss();

			try {
				JSONObject responseObject = json.getJSONObject("responseData");
				JSONArray resultArray = responseObject.getJSONArray("results");

				listImages = getImageList(resultArray);
				SetListViewAdapter(listImages);
				System.out.println("Result array length => " + resultArray.length());

				txtViewHasilTerjemahan.setText(hasilTerjemahan);			   
			} catch (JSONException e) {
				e.printStackTrace();
			}

		}
	}

	public ArrayList<Object> getImageList(JSONArray resultArray)
	{
		ArrayList<Object> listImages = new ArrayList<Object>();
		GoogleImageBean bean;

		try 
		{
			for(int i=0; i<resultArray.length(); i++)
			{
				JSONObject obj;
				obj = resultArray.getJSONObject(i);
				bean = new GoogleImageBean();

				bean.setTitle(obj.getString("title"));
				bean.setThumbUrl(obj.getString("tbUrl"));

				System.out.println("Thumb URL => " + obj.getString("tbUrl"));

				listImages.add(bean);
			} 
			return listImages;
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return null;
	}

	public void SetListViewAdapter(ArrayList<Object> images)
	{
		adapter = new ListViewImageAdapter(activity, images);
		listViewImages.setAdapter(adapter);
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
			
			if (tts.isLanguageAvailable(Locale.ENGLISH) == TextToSpeech.LANG_AVAILABLE && bhsDest.equals("English")) {
				btnBicara.setEnabled(true);
				tts.setLanguage(Locale.ENGLISH);
			}
			else if (tts.isLanguageAvailable(Locale.FRENCH) == TextToSpeech.LANG_AVAILABLE && bhsDest.equals("French")) {
				btnBicara.setEnabled(true);
				tts.setLanguage(Locale.FRENCH);
			}
			else if (tts.isLanguageAvailable(Locale.GERMAN) == TextToSpeech.LANG_AVAILABLE && bhsDest.equals("German")) {
				btnBicara.setEnabled(true);
				tts.setLanguage(Locale.GERMAN);
			}
			else if (tts.isLanguageAvailable(Locale.ITALIAN) == TextToSpeech.LANG_AVAILABLE && bhsDest.equals("Italian")) {
				btnBicara.setEnabled(true);
				tts.setLanguage(Locale.ITALIAN);
			}
			else if (tts.isLanguageAvailable(Locale.JAPANESE) == TextToSpeech.LANG_AVAILABLE && bhsDest.equals("Japanese")) {
				btnBicara.setEnabled(true);
				tts.setLanguage(Locale.JAPANESE);
			}
			else if (tts.isLanguageAvailable(Locale.KOREAN) == TextToSpeech.LANG_AVAILABLE && bhsDest.equals("Korean")) {
				btnBicara.setEnabled(true);
				tts.setLanguage(Locale.KOREAN);
			}
			else if (tts.isLanguageAvailable(Locale.SIMPLIFIED_CHINESE) == TextToSpeech.LANG_AVAILABLE && bhsDest.equals("Chinese Simplified")) {
				btnBicara.setEnabled(true);
				tts.setLanguage(Locale.SIMPLIFIED_CHINESE);
			}
			else if (tts.isLanguageAvailable(Locale.TRADITIONAL_CHINESE) == TextToSpeech.LANG_AVAILABLE && bhsDest.equals("Chinese Traditional")) {
				btnBicara.setEnabled(true);
				tts.setLanguage(Locale.TRADITIONAL_CHINESE);
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
