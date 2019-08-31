package com.example.stevie;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class HelpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajuda);

        setTitle("HelpActivity");

        ImageButton btnObjetivo = findViewById(R.id.btnObjetivo);
        ImageButton btnUtilizar = findViewById(R.id.btnUtilizar);
        ImageButton btnSobre = findViewById(R.id.btnSobre);
        ImageButton btnVoltar = findViewById(R.id.btnVoltar);

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HelpActivity.this, MenuPrincipal.class);
                startActivity(intent);
            }
        });
    }
}
