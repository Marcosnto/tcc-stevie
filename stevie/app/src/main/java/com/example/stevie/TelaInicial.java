package com.example.stevie;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;
import java.util.UUID;

public class TelaInicial extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private static final int REQUEST_ENABLE_BT = 1;

    ArrayList<String> mArrayAdapter; //array para armazenar os dispositivos conhecidos
    BluetoothAdapter mBluetoothAdapter;
    ArrayList<BluetoothDevice> mBTDevices = new ArrayList<>();
    BluetoothDevice dispositivo;

    private static final UUID MY_UUID_INSECURE = UUID.fromString("11111111-1111-1111-1111-111111111123");

    @Override
    protected void onDestroy() {
        Log.d(TAG, "onDestroy: called.");
        super.onDestroy();
        unregisterReceiver(mBroadcastReceiver1);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_inicial);
        Button btnONOFF = (Button) findViewById(R.id.btnBluetooth);
        Button search = (Button) findViewById(R.id.btnPesquisar);

        //retorna o bluetooth que representa o Bluetooth do smartphone
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();



        btnONOFF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: enabling/disabling bluetooth");
                enableDisableBT();
            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: pesquisando");
                verificarBluetooths();

                ConnectThread pai = new ConnectThread(dispositivo);
                pai.start();
            }
        });
    }

    //broadcast para avisos no log
    private final BroadcastReceiver mBroadcastReceiver1 = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            //Quando o descovery achar um dispositivo
            if (action.equals(mBluetoothAdapter.ACTION_STATE_CHANGED)) {
                final int state = intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, mBluetoothAdapter.ERROR);

                switch (state) {
                    case BluetoothAdapter.STATE_OFF:
                        Log.d(TAG, "onReceive: STATE OFF");
                        break;
                    case BluetoothAdapter.STATE_TURNING_OFF:
                        Log.d(TAG, "onReceive: STATE TURNING OFF");
                        break;
                    case BluetoothAdapter.STATE_ON:
                        Log.d(TAG, "onReceive: STATE ON");
                        break;
                    case BluetoothAdapter.STATE_TURNING_ON:
                        Log.d(TAG, "onReceive: STATE TURNING ON");
                        break;
                }
            }
        }
    };


    public void enableDisableBT() {
        if (mBluetoothAdapter == null) {
            //informar que o aparelho não suporta Bluetooth
            Log.d(TAG, "O aparelho não permite o uso do Bluetooth");
        }
        //verifica se o bluetooth está ativo, caso não esteja
        //ele irá ativar e ativar a descoberta dele por 300 segundos
        if (!mBluetoothAdapter.isEnabled()) {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
            enableDiscovery();

            Toast.makeText(this, "Bluetooth ativado", Toast.LENGTH_SHORT).show();

            //essa intente irá registrar o que ocorrer com o bluetooth no logcat
            IntentFilter BTIntent = new IntentFilter(BluetoothAdapter.ACTION_STATE_CHANGED);
            registerReceiver(mBroadcastReceiver1, BTIntent);
        }
        //caso seja apertado novamente, ele desabilita o botão
        if (mBluetoothAdapter.isEnabled()) {
            mBluetoothAdapter.disable();

            Toast.makeText(this, "Bluetooth desativado", Toast.LENGTH_SHORT).show();

            //essa intente irá registrar o que ocorrer com o bluetooth no logcat
            IntentFilter BTIntent = new IntentFilter(BluetoothAdapter.ACTION_STATE_CHANGED);
            registerReceiver(mBroadcastReceiver1, BTIntent);
        }

    }

    //ativa o dispositivo para descoberta
    public void enableDiscovery() {
        Log.d(TAG, "enableDiscovery: Discoverable for 300s");

        Intent discoverableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
        discoverableIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 300);
        startActivity(discoverableIntent);
    }

    //verificar se o dispositivo já é conhecido
    public void verificarBluetooths() {
        //verificar se o dispositivo já é conhecido
        Set<BluetoothDevice> pairedDevices = mBluetoothAdapter.getBondedDevices();
        //se existem dispositivos pareados
        if (pairedDevices.size() > 0) {
            //loop entre os dispositivos pareados
            for (BluetoothDevice device : pairedDevices) {
                //adiciona o nome e o endereço em um array de adapters para mostrar em um
                //ListView
                //mArrayAdapter.add(device.getName() + "\n" + device.getAddress());
                mBTDevices.add(device);
                Log.d(TAG, "verificarBluetooths: " + device.getName() + "\n" + device.getAddress());
                dispositivo = mBTDevices.get(0);
            }
        }
    }

    public void parearBluetooth() {
        //tem nada a ver isso aq
        Toast.makeText(this, "" + mBTDevices.get(0).getName(), Toast.LENGTH_SHORT).show();
        Toast.makeText(this, "" + mBTDevices.get(0).getAddress(), Toast.LENGTH_SHORT).show();
    }

    ;

    private class ConnectThread extends Thread {
        private final BluetoothSocket mmSocket;
        private final BluetoothDevice mmDevice;

        public ConnectThread(BluetoothDevice device) {
            BluetoothSocket tmp = null;
            mmDevice = device;

            try {
                tmp = device.createRfcommSocketToServiceRecord(MY_UUID_INSECURE);
            } catch (IOException e) {
            }
            mmSocket = tmp;
        }

        public void run() {
            // Cancel discovery because it will slow down the connection
            mBluetoothAdapter.cancelDiscovery();

            try {
                // Connect the device through the socket. This will block
                // until it succeeds or throws an exception
                mmSocket.connect();
            } catch (IOException connectException) {
                // Unable to connect; close the socket and get out
                try {
                    mmSocket.close();
                } catch (IOException closeException) {
                }
                return;
            }

            // Do work to manage the connection (in a separate thread)
            //manageConnectedSocket(mmSocket);
            Log.d(TAG, "run: foi");
        }

        /**
         * Will cancel an in-progress connection, and close the socket
         */
        public void cancel() {
            try {
                mmSocket.close();
            } catch (IOException e) {
            }
        }
    }

    public void aleluia(){
        Toast.makeText(this, "funcionou", Toast.LENGTH_SHORT).show();
    }
}
