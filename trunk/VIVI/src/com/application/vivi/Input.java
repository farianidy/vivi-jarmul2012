package com.application.vivi;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.widget.TextView;

public class Input extends Activity {

	private String bhsOrg, bhsDest;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);
        
        Intent i = getIntent();
        bhsOrg = i.getStringExtra("bhsOrg");
        bhsDest = i.getStringExtra("bhsDest");
        
        TextView txtViewBhsOrg = (TextView) findViewById(R.id.txtViewBhsOrg);
        TextView txtViewBhsDest = (TextView) findViewById(R.id.textViewBhsDest);
        
        txtViewBhsOrg.setText(bhsOrg);
        txtViewBhsDest.setText(bhsDest);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_input, menu);
        return true;
    }
}
