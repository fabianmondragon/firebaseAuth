package com.example.usuario.hellowordl;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import butterknife.BindView;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private static final String TAG = "Info";
    @BindView(R.id.btnIngresar) Button btnIngresar;
    @BindView(R.id.txtViewRegistrar) TextView textViewRegistrarse;
    @BindView(R.id.edtTextUser) EditText editTextUser;
    @BindView(R.id.edtPassword) EditText editTPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        FirebaseAuth auth = FirebaseAuth.getInstance();
        auth.addAuthStateListener(new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull final FirebaseAuth firebaseAuth) {
                final FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    Log.i("AuthStateChanged", "User is signed in with uid: " + user.getUid());
                } else {
                    Log.i("AuthStateChanged", "No user is signed in.");
                }
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }
     View.OnClickListener clickListener = new View.OnClickListener(){

         @Override
         public void onClick(View v) {
             switch ( v.getId() ) {
                 case R.id.txtViewRegistrar:
                     Intent intent = new Intent();
                     intent.setClass(MainActivity.this, RegisterActivity.class);
                     startActivity (intent);
                     break;
                 case R.id.btnIngresar:
                     break;
             }
         }
     };
}
