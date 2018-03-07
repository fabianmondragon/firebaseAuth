package com.example.usuario.hellowordl;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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

public class RegisterActivity extends AppCompatActivity {
    @BindView(R.id.edtTextRUser) EditText editTextUsuario;
    @BindView(R.id.btnRegistrar) Button btnRegistrar;
    @BindView(R.id.edtRPassword) EditText editTPassword;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private static final String TAG = "RegisterActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser =mAuth.getCurrentUser();
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch ( v.getId()) {
                case R.id.edtTextRUser:

                    break;

                case R.id.btnRegistrar:

                    registerFirebase (editTextUsuario.getText().toString(),editTPassword.getText().toString());

                break;

            }
        }
    };
    public void registerFirebase (String email, String password)
    {
        mAuth.createUserWithEmailAndPassword( email,  password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "createUserWithEmail:onComplete:" + task.isSuccessful());

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "sigIngWithEmail::sucees");
                            Toast.makeText(RegisterActivity.this,"Failed",
                                    Toast.LENGTH_SHORT).show();
                        }


                        // ...
                    }
                });

    }
}
