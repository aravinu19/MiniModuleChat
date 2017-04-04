package com.ivara.aravi.minimodulechat;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Main2Activity extends AppCompatActivity {

    FirebaseAuth mAuth;

    TextView e,p,t;

    Button r,s;

    ProgressDialog pf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        mAuth = FirebaseAuth.getInstance();

        pf = new ProgressDialog(this);

        r = (Button)findViewById(R.id.reg1);
        s = (Button)findViewById(R.id.sign);

        e = (TextView)findViewById(R.id.eml);
        p = (TextView)findViewById(R.id.pws);

        t = (TextView)findViewById(R.id.ss);

        r.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String em = e.getText().toString().trim();
                String pw = p.getText().toString().trim();

                if (em.isEmpty())
                {
                    Toast.makeText(getApplicationContext(),"Enter the Email Id !",Toast.LENGTH_SHORT).show();
                    return;
                }

                if (pw.isEmpty())
                {
                    Toast.makeText(getApplicationContext(),"Enter the Password !",Toast.LENGTH_SHORT).show();
                    return;
                }

                pf.setMessage("Registering User to Mini Module Servers !");
                pf.show();

                mAuth.createUserWithEmailAndPassword(em,pw)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful())
                                {
                                    Toast.makeText(getApplicationContext(),"Registration Successful !",Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(getApplicationContext(),MainActivity.class));
                                }
                                else
                                {
                                    Toast.makeText(getApplicationContext(),"Registration Failed !, pls Try Again . . .",Toast.LENGTH_SHORT).show();

                                }
                            }
                        });
                pf.dismiss();

            }
        });


        s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });


        t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });



    }
}
