package com.example.haroldsurface.cryptotool;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Encrypt extends AppCompatActivity {

    EditText plainText, keyText;
    TextView cipherText;
    Button encryptButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encrypt);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        plainText = (EditText) findViewById(R.id.plainTxt);
        keyText = (EditText) findViewById(R.id.keyTxt);
        cipherText = (TextView) findViewById(R.id.cipcherTxt);
        encryptButton = (Button) findViewById(R.id.encrytpBtn);


        encryptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String message = plainText.getText().toString();
                int key =  Integer.parseInt(keyText.getText().toString());
                cipherText.setText(shiftMessage(message, key));

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_encrypt, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private String shiftMessage(String message, int key){
        String cipher = "";
        message = message.toUpperCase();
        for (int i=0; i< message.length(); i++){
            char aChar = (message.charAt(i));
            if ((aChar > 'Z') || (aChar < 'A')){
                cipher += aChar;
                continue;
            }
            aChar = (char)(aChar + (key % 26));
            if (aChar > 'Z'){
                cipher += (char)(message.charAt(i) - (26 - (key % 26)) );
            } else {
                cipher += (char)(message.charAt(i) + (key % 26));
            }
        }
        return cipher;
    }
}
