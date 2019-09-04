package com.example.stevie;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.stevie.app.StevieApplication;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;
import java.util.UUID;

public class TelaInicial extends AppCompatActivity {
    private static final String TAG = "TelaInicial";
    BluetoothDevice dispositivo;
    BluetoothAdapter mBluetoothAdapter;
    ArrayList<BluetoothDevice> mBTDevices = new ArrayList<>();


    private static final UUID MY_UUID_INSECURE = UUID.fromString("04c6093b-0000-1000-8000-00805f9b34fb");



    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_inicial);
        Button search = (Button) findViewById(R.id.btnPesquisar);
        mBluetoothAdapter = mBluetoothAdapter.getDefaultAdapter();

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: pesquisando");
                verificarBluetooths();
                conectarServidor();
                Intent intent = new Intent(TelaInicial.this, MenuPrincipal.class);
                startActivity(intent);

//                ((StevieApplication) getApplication()).makeSocket(dispositivo, MY_UUID_INSECURE);
//                ConnectThread conexao = new ConnectThread();
//                conexao.run(((StevieApplication) getApplication()).getSocket(),
//                        ((StevieApplication) getApplication()).getDevice());



            }
        });
    }

    //verificar se o dispositivo já é conhecido
    public void verificarBluetooths() {
        if (mBluetoothAdapter == null) {
            Log.d(TAG, "verificarBluetooths: Smartphone não suporta");
            return;
        }
        Set<BluetoothDevice> pairedDevices = mBluetoothAdapter.getBondedDevices();
        if (pairedDevices.size() > 0) {
            for (BluetoothDevice device : pairedDevices) {
                mBTDevices.add(device);
                Log.d(TAG, "verificarBluetooths: " + device.getName() + "\n" + device.getAddress());
                if (device.getName().equals("DESKTOP-DC53JIR")) {
                    dispositivo = device;
                    Log.i(TAG, "verificarBluetooths: Peguei!" + " " + dispositivo.getName());
                }
            }
        }
    }

    public class ConnectThread extends Thread {

        public void run(BluetoothSocket mmSocket, BluetoothDevice device) {
            // Cancel discovery because it will slow down the connection
            mBluetoothAdapter.cancelDiscovery();
            try {
                //Conecta o dispositivo através do com.example.stevie.socket. Isso irá bloquear
                //até ter sucesso ou disparar uma excessão
                mmSocket.connect();
                Log.i(TAG, "run: Conectei!!!!!");
            } catch (IOException connectException) {
                // Unable to connect; close the com.example.stevie.socket and get out
                try {
                    mmSocket.close();
                } catch (IOException closeException) {
                    Log.e(TAG, "Could not close the client com.example.stevie.socket", closeException);
                }
                return;
            }

        }

    }

    public void conectarServidor(){
        ((StevieApplication) getApplication()).makeSocket(dispositivo, MY_UUID_INSECURE);
        ConnectThread conexao = new ConnectThread();
        conexao.run(((StevieApplication) getApplication()).getSocket(),
                ((StevieApplication) getApplication()).getDevice());
    }
}
