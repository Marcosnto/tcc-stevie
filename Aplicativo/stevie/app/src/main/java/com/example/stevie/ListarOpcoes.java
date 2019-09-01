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

public class ListarOpcoes extends AppCompatActivity {

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

//        ImageButton btnSelecionar=  findViewById(R.id.btnSelecionar);
//        ImageButton btnVoltar = findViewById(R.id.btnVoltar);
//
//        btnSelecionar.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(ListarOpcoes.this, Navegar.class);
//                startActivity(intent);
//            }
//        });
//
//        btnVoltar.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(ListarOpcoes.this, MenuPrincipal.class);
//                startActivity(intent);
//            }
//        });

        setupRecycler();
    }

    private void setupRecycler() {
        Log.d("Recycler", "got");

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));


        String[] mockedList = {"Cadeira",
                "Mesa",
                "Poste",
                "Fiquei sem ideia",
                "É isso",
                "Hmmm sei lá oq po vei",
                "Queijo tá demorando ein",
                "Se demorar mais vou zuar o projeto dele",
                "Cadeira",
                "Mesa",
                "Poste",
                "Fiquei sem ideia",
                "É isso",
                "Hmmm sei lá oq po vei",
                "Queijo tá demorando ein",
                "Se demorar mais vou zuar o projeto dele","Cadeira",
                "Mesa",
                "Poste",
                "Fiquei sem ideia",
                "É isso",
                "Hmmm sei lá oq po vei",
                "Queijo tá demorando ein",
                "Se demorar mais vou zuar o projeto dele"};

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
                Toast.makeText(ListarOpcoes.this, name, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(ListarOpcoes.this, Navegar.class);
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
