package app.com.eLearningApp.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import app.com.eLearningApp.R;

public class ResetPassword extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        final EditText email=findViewById(R.id.etEmailReset);
        final TextView msg=findViewById(R.id.tv_reset);
        final Button btReset=findViewById(R.id.btRest);
        btReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth auth = FirebaseAuth.getInstance();
                btReset.setClickable(false);
                email.setVisibility(View.GONE);
                auth.sendPasswordResetEmail(email.getText().toString().trim()).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            msg.setVisibility(View.VISIBLE);
                            msg.setText("We have sent an email. Check your email account for reset your password.");
                            btReset.setClickable(false);
                        }
                        else {
                            btReset.setClickable(true);
                            email.setVisibility(View.VISIBLE);
                            msg.setVisibility(View.GONE);
                            Toast.makeText(ResetPassword.this, "Something went wrong, try again.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

    }
}
