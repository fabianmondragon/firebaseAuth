package com.example.usuario.hellowordl;

import android.content.Intent;
import android.nfc.Tag;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private static final String TAG = "Info";
    @BindView(R.id.btnIngresar)
    Button btnIngresar;
    @BindView(R.id.txtViewRegistrar)
    TextView textViewRegistrarse;
    @BindView(R.id.edtTextUser)
    EditText editTextUser;
    @BindView(R.id.edtPassword)
    EditText editTPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        ButterKnife.bind(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();

        // mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
      /*  if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }*/
    }


    /**
     * Method for login with email and password
     *
     * @param email
     * @param password
     * @author Fabian Mondragon
     */
    public void sigInWithEmailAndPassword(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    FirebaseUser user = task.getResult().getUser();
                    String uudi = mAuth.getUid();
                    String name = user.getDisplayName();
                    String emial = user.getEmail();
                    String excelente = "";
                    Intent intent = new Intent();
                    intent.setClass(MainActivity.this, MainMenuActivity.class);
                    startActivity(intent);
                }
                else {
                    Log.w(TAG, "signInWithEmail:failure", task.getException());
                    Toast.makeText(MainActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    /**
     * method for admin clikc of this Activity
     *
     * @param v
     */
    @OnClick({R.id.btnIngresar, R.id.txtViewRegistrar})
    public void clickManager(View v) {
        switch (v.getId()) {
            case R.id.btnIngresar:
                sigInWithEmailAndPassword(editTextUser.getText().toString(), editTPassword.getText().toString());
                break;
            case R.id.txtViewRegistrar:
                break;
        }
    }
}



