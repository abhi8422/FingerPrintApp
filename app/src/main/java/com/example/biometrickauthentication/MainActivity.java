package com.example.biometrickauthentication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.hardware.fingerprint.FingerprintManagerCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
ImageView fingerimg;
TextView textView;
FingerprintManager fingerprintManager;
    @SuppressLint("ServiceCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fingerimg=findViewById(R.id.img_finger);
        textView=findViewById(R.id.textView);
        fingerprintManager= (FingerprintManager) getSystemService(FINGERPRINT_SERVICE);
        init();chcekPermission();

    }

    private void chcekPermission() {
        if(ActivityCompat.checkSelfPermission(this,Manifest.permission.USE_BIOMETRIC)!=PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.USE_BIOMETRIC},1001);
        }else{
            init();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode==1001){
            if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                init();
            }
        }
    }

    private void init() {
        FingerPrintHandler fingerPrintHandler=new FingerPrintHandler(this);
        fingerPrintHandler.StartAuthentication(fingerprintManager,null);
    }

}
