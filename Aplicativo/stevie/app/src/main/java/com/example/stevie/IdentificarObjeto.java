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

        ImageButton btnProximos = (ImageButton) findViewById(R.id.btnProximos);
        ImageButton btnEspecifico = (ImageButton) findViewById(R.id.btnEspecifico);
        ImageButton btnVoltar = (ImageButton) findViewById(R.id.btnVoltar);

        btnEspecifico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(IdentificarObjeto.this, SelecionarObjeto.class);
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
