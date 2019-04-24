package com.ufs.stevie_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SubMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_menu);

        setTitle("Menu Escolher Destino");

        Button voltarMenuPrincipal = (Button) findViewById(R.id.btnVoltar);
        voltarMenuPrincipal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent back = new Intent (SubMenuActivity.this, MainActivity.class);
                startActivity(back);
            }
        });
    }
}
