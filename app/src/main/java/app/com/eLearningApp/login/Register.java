package app.com.eLearningApp.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

import app.com.eLearningApp.Navigation;
import app.com.eLearningApp.R;

public class Register extends AppCompatActivity {
    public static final String TAG = "TAG";
    EditText mFullName;
    EditText mEmail;
    EditText mPassword;
//    EditText mPhone;
    Button mRegisterBtn;
    TextView mGotoLogin;
//    ProgressBar progressBar;
    String userID;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mFullName   = findViewById(R.id.etNameRegi);
        mEmail      = findViewById(R.id.etEmailRegi);
        mPassword   = findViewById(R.id.etPassRegi);
//        mPhone      = findViewById(R.id.phone);
        mRegisterBtn= findViewById(R.id.btRegi);
        mGotoLogin = findViewById(R.id.tvAHAA);
        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
//        progressBar = findViewById(R.id.pbRegi);
        if(fAuth.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(),Navigation.class));
            finish();
        }


        mRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = mEmail.getText().toString().trim();
                String password = mPassword.getText().toString().trim();
                final String fullName = mFullName.getText().toString();
                if (TextUtils.isEmpty(fullName)||TextUtils.isEmpty(email)||TextUtils.isEmpty(password)){
                    if(TextUtils.isEmpty(fullName)){
                        mEmail.setError("name is Required.");
                        return;
                    }
                    if(TextUtils.isEmpty(email)){
                        mEmail.setError("Email is Required.");
                        return;
                    }
                    if(TextUtils.isEmpty(password)){
                        mPassword.setError("Password is Required.");
                        return;
                    }
                }
                else if(password.length() < 6){
                    mPassword.setError("Password Must be >= 6 Characters");
                    return;
                }else {

                mRegisterBtn.setBackground(getDrawable(R.drawable.shape_butten_grey));

                // register the user in firebase

                fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            Toast.makeText(Register.this, "User Created.", Toast.LENGTH_SHORT).show();
                            userID = fAuth.getCurrentUser().getUid();
                            DocumentReference documentReference = fStore.collection("users").document(userID);
                            Map<String, Object> user = new HashMap<>();
                            user.put("fName", fullName);
                            user.put("email", email);
                            documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Log.d(TAG, "onSuccess: user Profile is created for " + userID);
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.d(TAG, "onFailure: " + e.toString());
                                }
                            });
                            startActivity(new Intent(getApplicationContext(), Navigation.class));

                        } else {
                            if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                                Toast.makeText(Register.this, "Email already registered", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(Register.this, "Error ! " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                            }
                        }
                        mRegisterBtn.setBackground(getDrawable(R.drawable.shape_butten_primery));
                    }
                });
                }
            }
        });



        mGotoLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Login.class));
            }
        });


    }
}
