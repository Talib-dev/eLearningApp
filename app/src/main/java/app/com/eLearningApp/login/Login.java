package app.com.eLearningApp.login;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import java.util.Objects;

import app.com.eLearningApp.Navigation;
import app.com.eLearningApp.R;
import app.com.eLearningApp.classes.Constant;

public class Login extends AppCompatActivity {
    private static final int RC_SIGN_IN = 1;
    private EditText mEmail,mPassword;
    private Button mLoginBtn;
    private TextView mRegisterBtn;
    private TextView mForgetPass;
//    private ProgressBar progressBar;
    private FirebaseAuth fAuth;
    private LinearLayout signInButton;
    private GoogleSignInClient signInClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
        setData();
    }

    void init(){

        mEmail = findViewById(R.id.etEmailLogin);
        mPassword = findViewById(R.id.etPassLogin);
//        progressBar = findViewById(R.id.pbLogin);
        mForgetPass=findViewById(R.id.tvForgetPassLogin);
        mLoginBtn = findViewById(R.id.btLogin);
        mRegisterBtn = findViewById(R.id.tvRegister);
        signInButton=findViewById(R.id.llGoogleLogin);

    }
    void setData(){
        //Google signIn Configuration
        mForgetPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this,ResetPassword.class));
            }
        });
        fAuth = FirebaseAuth.getInstance();
        GoogleSignInOptions gso= new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        signInClient= GoogleSignIn.getClient(this,gso);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SingInUSingGoogle();
            }
        });

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if(user != null) {
            startActivity(new Intent(getApplicationContext(), Navigation.class));
            this.finish();
        }
        else {
            mLoginBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String email = mEmail.getText().toString().trim();
                    String password = mPassword.getText().toString().trim();
                    if (TextUtils.isEmpty(email)||TextUtils.isEmpty(password)){
                        if (TextUtils.isEmpty(email)) {
                            mEmail.setError("Email is Required.");
                            return;
                        }

                        if (TextUtils.isEmpty(password)) {
                            mPassword.setError("Password is Required.");
                            return;
                        }
                    }
                    else if (password.length() < 6) {
                        mPassword.setError("Password Must be >= 6 Characters");
                        return;
                    }
                    else {
                    mLoginBtn.setBackground(getDrawable(R.drawable.shape_butten_grey));

                    // authenticate the user

                    fAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Intent intent=new Intent(getApplicationContext(), Navigation.class);
                                intent.putExtra(Constant.loginIntent,"normal");
                                startActivity(intent);
                                finish();

                            } else {
                                Toast.makeText(Login.this, "Error ! " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                            mLoginBtn.setBackground(getDrawable(R.drawable.shape_butten_primery));
                        }
                    });

                }
                }
            });


            mRegisterBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getApplicationContext(), Register.class));
                    finish();
                }
            });


        }

    }

    private void SingInUSingGoogle() {
        Intent SigmInintent=signInClient.getSignInIntent();
        startActivityForResult(SigmInintent,RC_SIGN_IN);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==RC_SIGN_IN && data!=null){
        Task<GoogleSignInAccount> task=GoogleSignIn.getSignedInAccountFromIntent(data);
        handelSignInResults(task);}
    }

    private void handelSignInResults(Task<GoogleSignInAccount> task) {
        try {
            GoogleSignInAccount signInAccount=task.getResult(ApiException.class);
            FireBaseGoogleAuth(Objects.requireNonNull(signInAccount));

        }catch (ApiException e){
            Toast.makeText(this, "Sign In Failed", Toast.LENGTH_SHORT).show();
//            FireBaseGoogleAuth(null);



        }
    }

    private void FireBaseGoogleAuth(GoogleSignInAccount signInAccount) {
        AuthCredential authCredential= GoogleAuthProvider.getCredential(signInAccount.getIdToken(),null);
        fAuth.signInWithCredential(authCredential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Toast.makeText(Login.this, "SignIn SuccessFul", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(getApplicationContext(), Navigation.class);
                    intent.putExtra(Constant.loginIntent,"google");
                    startActivity(intent);
                    finish();
                }
                else {
                    Toast.makeText(Login.this, "SignIn SuccessFul", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
