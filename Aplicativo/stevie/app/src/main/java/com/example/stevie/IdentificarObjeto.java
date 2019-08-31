package com.example.stevie;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class IdentificarObjeto extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_identificar_objeto);

        setTitle("Identificar Objeto");

        ImageButton btnProximos = findViewById(R.id.btnProximos);
        ImageButton btnEspecifico = findViewById(R.id.btnEspecifico);
        ImageButton btnVoltar = findViewById(R.id.btnVoltar);

        btnEspecifico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(IdentificarObjeto.this, ChooseOptionActivity.class);
                intent.putExtra("title", "Selecionar Objeto");
                startActivity(intent);
            }
        });

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(IdentificarObjeto.this, MenuPrincipal.class);
                startActivity(intent);
            }
        });
    }
}
