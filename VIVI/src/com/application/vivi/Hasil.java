package com.application.vivi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import android.app.Activity;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.application.vivi.adapters.GoogleImageBean;
import com.application.vivi.adapters.ListViewImageAdapter;
import com.memetix.mst.language.Language;
import com.memetix.mst.translate.Translate;


public class Hasil extends Activity {
/** Called when the activity is first created. */
	
	//==
	private String bhsOrg, bhsDest, hasil;
	public String translatedText, hasil2;
	
	
	private ListView listViewImages;
	private EditText txtSearchText;	
	
    private ListViewImageAdapter adapter;
    private ArrayList<Object> listImages;
	private Activity activity;
	
	private TextView txtViewHasilTerjemahan;
	
	String strSearch = null;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        //==
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
        txtViewHasilTerjemahan.setText("Your translated text here..");
        
        //==
        activity = this;
        listViewImages = (ListView) findViewById(R.id.lviewImages);
        
 	    strSearch = Uri.encode(hasil);
 	   
 	    System.out.println("Search string => "+  strSearch);
 	    new getImagesTask().execute();
    }
    
   public class getImagesTask extends AsyncTask<Void, Void, Void>
   {
	   JSONObject json;
	   ProgressDialog dialog;
	   
	   @Override
	   protected void onPreExecute() {
		   // TODO Auto-generated method stub
		   super.onPreExecute();
		   
		   dialog = ProgressDialog.show(Hasil.this, "", "Please wait...");
	   }
	   
	   @Override
	   protected Void doInBackground(Void... params) {
		   // TODO Auto-generated method stub
		   
		   URL url;
		   try {
			   url = new URL("https://ajax.googleapis.com/ajax/services/search/images?" +
			   	"v=1.0&q="+strSearch+"&rsz=8"); //&key=ABQIAAAADxhJjHRvoeM2WF3nxP5rCBRcGWwHZ9XQzXD3SWg04vbBlJ3EWxR0b0NVPhZ4xmhQVm3uUBvvRF-VAA&userip=192.168.0.172");
		   
			   URLConnection connection = url.openConnection();
			   connection.addRequestProperty("Referer", "http://technotalkative.com");
			   
			   String line;
			   StringBuilder builder = new StringBuilder();
			   BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			   while((line = reader.readLine()) != null) {
				   builder.append(line);
			   }
	
			   System.out.println("Builder string => "+builder.toString());
			   
			   json = new JSONObject(builder.toString());
			   
			   //==
			   
			   //==
		   } catch (MalformedURLException e) {
			   // TODO Auto-generated catch block
			   e.printStackTrace();
		   } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		   
		//==
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
		   
		   return null;
	   }
	   
	   @Override
	   protected void onPostExecute(Void result) {
		   // TODO Auto-generated method stub
		   super.onPostExecute(result);
		   
		   if(dialog.isShowing())
		   {
			   dialog.dismiss();
		   }
		   
		   try {
			   JSONObject responseObject = json.getJSONObject("responseData");
			   JSONArray resultArray = responseObject.getJSONArray("results");
			   
			   listImages = getImageList(resultArray);
			   SetListViewAdapter(listImages);
			   System.out.println("Result array length => "+resultArray.length());
			   
			   txtViewHasilTerjemahan.setText(translatedText);
			   
		   } catch (JSONException e) {
			// TODO Auto-generated catch block
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
				
				System.out.println("Thumb URL => "+obj.getString("tbUrl"));
				
				listImages.add(bean);
			   
			} 
			return listImages;
		 }
		 catch (JSONException e) 
		 {
				// TODO Auto-generated catch block
				e.printStackTrace();
		 }
		 
		 return null;
	}
   
   public void SetListViewAdapter(ArrayList<Object> images)
   {
	   adapter = new ListViewImageAdapter(activity, images);
	   listViewImages.setAdapter(adapter);
   }
   


}
