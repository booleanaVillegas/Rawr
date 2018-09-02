package com.villegas.juliana.taller1_julivillegas_controlandroid;

import android.app.Activity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
    }
    public void restart(View v){
new Task().execute("reiniciar");
    }
    public void saltar(View v){
new Task().execute("saltar");
    }
    public void disparar(View v){
new Task().execute("disparar");
    }

}
