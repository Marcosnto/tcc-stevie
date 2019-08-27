package com.example.stevie;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;

public class Navegar extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navegar);

        setTitle("Navegar");

        ImageButton btnMenuAnterior = (ImageButton) findViewById(R.id.btnAnterior);
        ImageButton btnMenuPrincipal = (ImageButton) findViewById(R.id.btnPrincipal);

        btnMenuAnterior.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Navegar.this, SelecionarObjeto.class);
                startActivity(intent);
            }
        });

        btnMenuPrincipal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Navegar.this, MenuPrincipal.class);
                startActivity(intent);
            }
        });
    }
}
