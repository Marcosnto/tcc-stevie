package com.ufs.stevie_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HelpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        setTitle("Ajuda");

        Button voltar = (Button) findViewById(R.id.btnVoltar);
        voltar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent it = new Intent(HelpActivity.this, MainActivity.class);
                startActivity(it);
            }
        });
    }
}
