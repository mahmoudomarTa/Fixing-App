package com.example.fixingapp.firebase;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

import androidx.annotation.NonNull;

public class Authentication {
    static FirebaseAuth auth = FirebaseAuth.getInstance();
    String VERIFICATION_CODE_ID ="";
    Activity activity;
    OnSignUpListener onSignUpListener;

    public static String UID;

    public void setOnSignUpListener(OnSignUpListener onSignUpListener) {
        this.onSignUpListener = onSignUpListener;
    }

    public Authentication(Activity activity) {
        this.activity = activity;
    }

    public void sendVerificationCode(String phoneNumber){
        Log.d("momo","sendVerificationCode is invoked");
        PhoneAuthProvider.getInstance().verifyPhoneNumber(phoneNumber
                , 60
                , TimeUnit.SECONDS
                , TaskExecutors.MAIN_THREAD
                , new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                    @Override
                    public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                        String code=phoneAuthCredential.getSmsCode();
                        if (code!=null){
                            onSignUpListener.onCodeSent(code);
                            signUp(code);
                            Log.d("momo","1-onVerificationCompleted is invoked code is not null : "+code);
                        }
                    }

                    @Override
                    public void onVerificationFailed(@NonNull FirebaseException e) {
                            onSignUpListener.onCodeDosesNotSent(e);
                        Log.d("momo","2-onVerificationFailed is invoked ");

                    }

                    @Override
                    public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                        super.onCodeSent(s, forceResendingToken);
                        VERIFICATION_CODE_ID = s;
                        Log.d("momo","3-onCodeSent is invoked ");

                    }
                });

    }
    public void signUp(String code){
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(VERIFICATION_CODE_ID,code);
        Log.d("momo","4-signUp is invoked ");

        auth.signInWithCredential(credential).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                    onSignUpListener.onSignUpSuccess();
                    UID=auth.getCurrentUser().getUid();
                Log.d("momo","5-signUp is invoked onSuccess");


            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                    onSignUpListener.onSignUpFailed(e);
                Log.d("momo","5-signUp is invoked onFailure error : "+e.getMessage());

            }
        });

    }

    public interface OnSignUpListener{
        void onCodeSent(String code);
        void onCodeDosesNotSent(Exception e);
        void onSignUpSuccess();
        void onSignUpFailed(Exception e);
    }


}
