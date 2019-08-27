package com.example.stevie;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MenuPrincipal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);


        setTitle("Menu Principal");

        ImageButton btnLocalizacao = (ImageButton) findViewById(R.id.btnLocalizacao);
        ImageButton btnIdentificar = (ImageButton) findViewById(R.id.btnIdentificar);
        ImageButton btnEscolherDestino = (ImageButton) findViewById(R.id.btnEscolherDestino);
        ImageButton btnAjuda = (ImageButton) findViewById(R.id.btnAjuda);

        btnIdentificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuPrincipal.this, IdentificarObjeto.class);
                startActivity(intent);
            }
        });

        btnEscolherDestino.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuPrincipal.this, EscolherDestino.class);
                startActivity(intent);
            }
        });

        btnAjuda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (MenuPrincipal.this, Ajuda.class);
                startActivity(intent);
            }
        });
    }

}
