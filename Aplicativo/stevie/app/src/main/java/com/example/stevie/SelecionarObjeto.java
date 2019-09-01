package com.example.stevie;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class SelecionarObjeto extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selecionar_opcao);

        setTitle("Selecionar Objeto");

//        ImageButton btnSelecionar=  findViewById(R.id.btnSelecionar);
//        ImageButton btnVoltar = findViewById(R.id.btnVoltar);
//
//        btnSelecionar.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(SelecionarObjeto.this, Navegar.class);
//                startActivity(intent);
//            }
//        });
//
//        btnVoltar.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(SelecionarObjeto.this, MenuPrincipal.class);
//                startActivity(intent);
//            }
//        });

    }
}
