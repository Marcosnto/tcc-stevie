package com.ufs.stevie_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        TextView textMenuPrincipal = (TextView) findViewById(R.id.textMenuPrincipal);
//
//        Typeface font = Typeface.createFromAsset(getAssets(), getString(R.string.calibrib_ttf));
//        textMenuPrincipal.setTypeface(font);

        setTitle("Menu Principal");

        ImageView goSubMenu = (ImageView) findViewById(R.id.btnOpcao3);
        goSubMenu.setOnClickListener(new View.OnClickListener(){
            public void onClick (View view){
                Intent it = new Intent (MainActivity.this, SubMenuActivity.class);
                startActivity(it);
            }
        });

        ImageView goHelp = (ImageView) findViewById(R.id.btnAjuda);
        goHelp.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent it = new Intent(MainActivity.this, HelpActivity.class);
                startActivity(it);
            }
        });
    }
}
