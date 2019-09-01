package com.example.stevie;

import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import com.example.stevie.app.StevieApplication;

public class MenuPrincipal extends AppCompatActivity{
//  DataThread dataThread = new DataThread(((StevieApplication) getApplication()).getSocket());
    private static final String TAG = "MenuPrincipal";
    BluetoothSocket bluetoothSocket = ((StevieApplication) getApplication()).getSocket();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);

        setTitle("Menu Principal");

        ImageButton btnLocalizacao = (ImageButton) findViewById(R.id.btnLocalizacao);
        ImageButton btnIdentificar = (ImageButton) findViewById(R.id.btnIdentificar);
        ImageButton btnEscolherDestino = (ImageButton) findViewById(R.id.btnEscolherDestino);
        ImageButton btnAjuda = (ImageButton) findViewById(R.id.btnAjuda);

        btnLocalizacao.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

            }
        });

        btnIdentificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuPrincipal.this, IdentificarObjeto.class);
                startActivity(intent);
                String teste = "testando";
//                dataThread.write(teste.getBytes());
                Log.i(TAG, "onClick: enviei caralhõn");
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
                Intent intent = new Intent (MenuPrincipal.this, HelpActivity.class);
                startActivity(intent);
            }
        });
    }

}
