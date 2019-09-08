package com.example.stevie;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class EscolherDestino extends AppCompatActivity {

    ChooseOptionActivity listarEscolha = new ChooseOptionActivity();

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

        btnDepartamento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] departamentos = {
                        "Departamento de Matemática",
                        "Departamento de Sistemas de Informação"
                };
                Intent intent = new Intent(EscolherDestino.this, ChooseOptionActivity.class);
                intent.putExtra("options", departamentos);
                startActivity(intent);
            }
        });

        btnSala.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] salas = {
                        "Sala de Aula 101",
                        "Sala de Aula 102",
                        "Sala de Aula 103"
                };
                Intent intent = new Intent(EscolherDestino.this, ChooseOptionActivity.class);
                intent.putExtra("options", salas);
                startActivity(intent);            }
        });

        btnBanheiro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] banheiro = {
                        ""
                };
                Intent intent = new Intent(EscolherDestino.this, ChooseOptionActivity.class);
                intent.putExtra("options", banheiro);
                startActivity(intent);
            }
        });

        btnLaboratorio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] laboratorio = {
                        "Laboratório de Sistemas de Informação"
                };
                Intent intent = new Intent(EscolherDestino.this, ChooseOptionActivity.class);
                intent.putExtra("options", laboratorio);
                startActivity(intent);            }
        });

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EscolherDestino.this, MenuPrincipal.class);
                startActivity(intent);
            }
        });

    }
}
