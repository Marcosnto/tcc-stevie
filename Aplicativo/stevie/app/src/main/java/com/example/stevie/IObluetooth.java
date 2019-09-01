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
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Set;
import java.util.UUID;

public class IObluetooth extends AppCompatActivity {

    private static final String TAG = "IOBluetooth";
    private Handler handler;

    BluetoothAdapter mBluetoothAdapter;
    ArrayList<BluetoothDevice> mBTDevices = new ArrayList<>();

    private interface MessageConstants {
        public static final int MESSAGE_READ = 0;
        public static final int MESSAGE_WRITE = 1;
        public static final int MESSAGE_TOAST = 2;
    }

    @Override
    protected void onDestroy() {
        Log.d(TAG, "onDestroy: called.");
        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_inicial);

        //retorna o bluetooth que representa o Bluetooth do smartphone
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

    }
//
//
//    public class DataThread extends Thread {
//        private final BluetoothSocket mmSocket;
//        private final InputStream mmInStream;
//        private final OutputStream mmOutStream;
//        private byte[] mmBuffer; // mmBuffer store for the stream
//
//        public DataThread(BluetoothSocket com.example.stevie.socket) {
//            mmSocket = com.example.stevie.socket;
//            InputStream tmpIn = null;
//            OutputStream tmpOut = null;
//
//            // Get the input and output streams; using temp objects because
//            // member streams are final.
//            try {
//                tmpIn = com.example.stevie.socket.getInputStream();
//            } catch (IOException e) {
//                Log.e(TAG, "Error occurred when creating input stream", e);
//            }
//            try {
//                tmpOut = com.example.stevie.socket.getOutputStream();
//            } catch (IOException e) {
//                Log.e(TAG, "Error occurred when creating output stream", e);
//            }
//            mmInStream = tmpIn;
//            mmOutStream = tmpOut;
//        }
//
//        public void run() {
//            mmBuffer = new byte[1024];
//            int numBytes; // bytes que irão retornar da leitura
//
//            // Continua ouvindo até ocorrer uma excessão
//            while (true) {
//                try {
//                    Log.i(TAG, "run: escuta ativada");
//                    // Read from the InputStream.
//                    numBytes = mmInStream.read(mmBuffer);
//                    // Send the obtained bytes to the UI activity.
//                    Message readMsg = handler.obtainMessage(
//                            MessageConstants.MESSAGE_READ, numBytes, -1,
//                            mmBuffer);
//                    readMsg.sendToTarget();
//                } catch (IOException e) {
//                    Log.d(TAG, "Input stream was disconnected", e);
//                    break;
//                }
//            }
//        }
//
//        // Call this from the main activity to send data to the remote device.
//        public void write(byte[] bytes) {
//            try {
//                mmOutStream.write(bytes);
//
//                // Share the sent message with the UI activity.
//                Message writtenMsg = handler.obtainMessage(
//                        MessageConstants.MESSAGE_WRITE, -1, -1, mmBuffer);
//                writtenMsg.sendToTarget();
//                Log.i(TAG, "write: Dados enviados!");
//            } catch (IOException e) {
//                Log.e(TAG, "Ocorreu um erro ao enviar os dados", e);
//
//                // Send a failure message back to the activity.
//                Message writeErrorMsg =
//                        handler.obtainMessage(MessageConstants.MESSAGE_TOAST);
//                Bundle bundle = new Bundle();
//                bundle.putString("toast",
//                        "Couldn't send data to the other device");
//                writeErrorMsg.setData(bundle);
//                handler.sendMessage(writeErrorMsg);
//            }
//        }
//
//        // Call this method from the main activity to shut down the connection.
//        public void cancel() {
//            try {
//                mmSocket.close();
//            } catch (IOException e) {
//                Log.e(TAG, "Não foi possível encerrar a conexão", e);
//            }
//        }
//    }

}
