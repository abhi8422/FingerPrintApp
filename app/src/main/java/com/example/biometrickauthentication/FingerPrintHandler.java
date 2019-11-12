package com.example.biometrickauthentication;

import android.app.Activity;
import android.content.Context;
import android.hardware.fingerprint.FingerprintManager;
import android.os.CancellationSignal;

import android.widget.ImageView;
import android.widget.Toast;


public class FingerPrintHandler extends  FingerprintManager.AuthenticationCallback {
    Context context;
    public FingerPrintHandler(Context context) {
        this.context=context;
    }
    public void StartAuthentication(FingerprintManager fingerprintManagerCompat,
                                    FingerprintManager.CryptoObject cryptoObject){
        CancellationSignal cancellationSignal=new CancellationSignal();
        fingerprintManagerCompat.authenticate(cryptoObject,cancellationSignal,0,this,null);
    }

    @Override
    public void onAuthenticationError(int errMsgId, CharSequence errString) {
        super.onAuthenticationError(errMsgId, errString);
        Toast.makeText(context,"",Toast.LENGTH_SHORT).show();
    }

    private void updateUI() {
        ImageView imageView = (ImageView) ((Activity)context).findViewById(R.id.img_finger);
        imageView.setImageResource(R.drawable.ic_thumb);

    }

    @Override
    public void onAuthenticationSucceeded(FingerprintManager.AuthenticationResult result) {
        super.onAuthenticationSucceeded(result);
        this.updateUI();

    }
}
