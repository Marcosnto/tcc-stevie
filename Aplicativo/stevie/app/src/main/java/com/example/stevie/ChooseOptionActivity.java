package com.example.stevie;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.stevie.adapter.ObjectRecyclerAdapter;

public class ChooseOptionActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_selecionar_opcao);

        recyclerView = findViewById(R.id.objectRecycler);

        String title = getIntent().getStringExtra("title");

        if (title != null) {
            setTitle(title);
        }

        ImageButton btnSelecionar=  findViewById(R.id.btnSelecionar);
        ImageButton btnVoltar = findViewById(R.id.btnVoltar);

        btnSelecionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChooseOptionActivity.this, Navegar.class);
                startActivity(intent);
            }
        });

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChooseOptionActivity.this, MenuPrincipal.class);
                startActivity(intent);
            }
        });

        setupRecycler();
    }

    private void setupRecycler() {
        Log.d("Recycler", "got");

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));


        String[] mockedList = {
                "Sala 1",
                "Sala 2",
                "Sala 3",
                "Hall - Bloco D",
                "Sala - Professor - DSI",
                "Sala - PRofessor - DMAI",
                "DEMAI",
        };

        String[] mockedList2 = {"Cadeira",
                "Mesa",
                "Poste",
                "Fiquei sem ideia",
                "É isso",
                "Hmmm sei lá oq po vei",
                "Queijo tá demorando ein",
                "Se demorar mais vou zuar o projeto dele"};


        // specify an adapter (see also next example)
        mAdapter = new ObjectRecyclerAdapter(mockedList, new ObjectRecyclerAdapter.ChooseOptionInterface() {
            @Override
            public void onOptionClicked(String name) {
                Toast.makeText(ChooseOptionActivity.this, name, Toast.LENGTH_LONG).show();
                new SocketEnviar("destino:"  +name, "192.168.50.45", 7000).send();
                Intent intent = new Intent(ChooseOptionActivity.this, Navegar.class);
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }

    public interface ChooseOptionInterface {
        void onOptionClicked(String name);
    }
}
