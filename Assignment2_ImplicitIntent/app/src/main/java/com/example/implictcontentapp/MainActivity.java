package com.example.implictcontentapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.PhoneNumberUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;



public class MainActivity extends AppCompatActivity {
    private static final int CALL_PHONE_PERMISSION=100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("WebLink & PhoneCall Activity");
    }

    @Override
    protected void onStart() {
        super.onStart();
        int checkCallPerm=ContextCompat.checkSelfPermission(this, android.Manifest.permission.CALL_PHONE);
        if(checkCallPerm!=PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]{android.Manifest.permission.CALL_PHONE},CALL_PHONE_PERMISSION);
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
    public void closeApp(View view){
    finish();
    }
    public void openUrl(View view) {
        EditText urlText = findViewById(R.id.editTextUrl);
        String url = urlText.getText().toString();
        if(!url.isEmpty()){
            if(!url.startsWith("http")) url="https://"+url;
            Uri uri = Uri.parse(url);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        }
        else{
            Toast.makeText(this, "Empty Url", Toast.LENGTH_SHORT).show();
        }
    }

    public void openCaller(View view) {
        EditText phoneText = findViewById(R.id.editTextPhone);
        String phoneNumber = phoneText.getText().toString();
        if(PhoneNumberUtils.isGlobalPhoneNumber(phoneNumber)){

            Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:".concat(phoneNumber.toString())));
            startActivity(intent);
        }
        else{
            Toast.makeText(this, "Invalid Phone No", Toast.LENGTH_SHORT).show();
        }
    }
}