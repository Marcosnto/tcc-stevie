package com.example.stevie;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class EscolherDestino extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_escolher_destino);

        setTitle("Escolher Destino");

        ImageButton btnDepartamento = (ImageButton) findViewById(R.id.btnDepartamento);
        ImageButton btnSala = (ImageButton) findViewById(R.id.btnSala);
        ImageButton btnBanheiro = (ImageButton) findViewById(R.id.btnBanheiro);
        ImageButton btnLaboratorio = (ImageButton) findViewById(R.id.btnLaboratorio);
        ImageButton btnVoltar = (ImageButton) findViewById(R.id.btnVoltar);

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EscolherDestino.this, MenuPrincipal.class);
                startActivity(intent);
            }
        });

    }
}
