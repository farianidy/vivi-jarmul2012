package com.application.vivi;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class HasilTerjemahan extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hasil_terjemahan);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_hasil_terjemahan, menu);
        return true;
    }
}
