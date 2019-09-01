package com.example.stevie;

import android.view.Menu;
import android.widget.Toast;

import java.io.Serializable;

public class Dado implements Serializable {

    public byte[] ioDado;

    public Dado() {
    }

    public byte[] getIoDado() {
        return ioDado;
    }

    public void setIoDado(byte[] ioDado) {
        this.ioDado = ioDado;
    }


    public void menu(int dado) {

        switch (dado) {
            case 1:
                //.makeText(, "", Toast.LENGTH_SHORT).show();
                break;
        }
    }

}
