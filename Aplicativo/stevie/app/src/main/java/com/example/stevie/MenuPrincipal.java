package com.example.stevie;

import android.content.Intent;
import android.net.wifi.WifiManager;
import android.os.StrictMode;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.Formatter;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import java.util.Locale;

public class MenuPrincipal extends AppCompatActivity {
    private EditText write;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);

        // Set enable network access
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy
                .Builder()
                .permitAll()
                .build();
        StrictMode.setThreadPolicy(policy);

        WifiManager wm = (WifiManager) getApplicationContext().getSystemService(WIFI_SERVICE);
        String ip = Formatter.formatIpAddress(wm.getConnectionInfo().getIpAddress());
        new Thread(new SocketEnviar("ip:" + ip, "192.168.50.45", 7000)).start();

        setTitle("Menu Principal");

        ImageButton btnLocalizacao = (ImageButton) findViewById(R.id.btnLocalizacao);
        ImageButton btnIdentificar = (ImageButton) findViewById(R.id.btnIdentificar);
        ImageButton btnEscolherDestino = (ImageButton) findViewById(R.id.btnEscolherDestino);
        ImageButton btnAjuda = (ImageButton) findViewById(R.id.btnAjuda);

        btnLocalizacao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new SocketEnviar("localizacaoAtual", "192.168.50.45", 7000)).start();
                SocketReceber dado = new SocketReceber(getApplicationContext());
                Thread d = new Thread(dado);
                d.start();
            }
        });

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
                Intent intent = new Intent (MenuPrincipal.this, HelpActivity.class);
                startActivity(intent);
            }
        });
    }

}
