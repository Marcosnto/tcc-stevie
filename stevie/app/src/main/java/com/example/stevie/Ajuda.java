package com.example.stevie;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Ajuda extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajuda);

        setTitle("Ajuda");

        ImageButton btnObjetivo = (ImageButton) findViewById(R.id.btnObjetivo);
        ImageButton btnUtilizar = (ImageButton) findViewById(R.id.btnUtilizar);
        ImageButton btnSobre = (ImageButton) findViewById(R.id.btnSobre);
        ImageButton btnVoltar = (ImageButton) findViewById(R.id.btnVoltar);

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Ajuda.this, MenuPrincipal.class);
                startActivity(intent);
            }
        });
    }
}
