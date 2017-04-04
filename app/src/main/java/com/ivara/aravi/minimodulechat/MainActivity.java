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

import com.firebase.client.Firebase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    FirebaseAuth pf;

    FirebaseAuth.AuthStateListener authStateListener;

    ProgressDialog pd;

    TextView e,p;

    Button r,s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pf = FirebaseAuth.getInstance();



        pd = new ProgressDialog(this);

        e = (TextView)findViewById(R.id.eml);
        p = (TextView)findViewById(R.id.pws);

        r = (Button)findViewById(R.id.reg1);
        s = (Button)findViewById(R.id.login);

        r.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Main2Activity.class));
            }
        });

//        final String[] c = new String[1];

        final int[] tt = new int[2];
        tt[0] = 0;
        tt[1] = 0;
        s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pd.setMessage("Logging In . . .");
                pd.show();

                pf = FirebaseAuth.getInstance();
                String em = e.getText().toString().trim();
                String pw = p.getText().toString().trim();

                check();


                if (em.isEmpty())
                {
                    Toast.makeText(getApplicationContext(),"Email Field is Necessary !",Toast.LENGTH_SHORT).show();
                    return;
                }

                if (pw.isEmpty())
                {
                    Toast.makeText(getApplicationContext(),"Password Is Necessary !",Toast.LENGTH_SHORT).show();
                    return;
                }



                pf.signInWithEmailAndPassword(em,pw)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful())
                                {
                                    Toast.makeText(getApplicationContext(),"Login Successful !",Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(getApplicationContext(),Main3Activity.class));
                                }
                                else
                                {
                                    Toast.makeText(getApplicationContext(),"Credentials Mismatch !",Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                pd.dismiss();

            }
        });


    }

    private void check() {
        pf.addAuthStateListener(authStateListener);
        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (pf.getCurrentUser()!=null)
                {
                    Toast.makeText(getApplicationContext(),"Previous Login remembered !",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(),Main3Activity.class));
                }
            }
        };
    }

    @Override
    protected void onStart() {
        super.onStart();


    }

}
