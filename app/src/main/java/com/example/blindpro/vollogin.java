package com.example.blindpro;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class vollogin extends AppCompatActivity {

    public static final String TAG = "TAG";
    TextView mEmail,mPassword;
    Button mLoginBtn;
    TextView mCreateBtn,forgotTextLink;
    ProgressBar progressBar;
    FirebaseAuth fAuth;
    FirebaseFirestore firestore;
    int flag=0;
    List<String> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vollogin);

        mEmail = findViewById(R.id.Email);
        mPassword = findViewById(R.id.password);
        progressBar = findViewById(R.id.progressBar);
        fAuth = FirebaseAuth.getInstance();
        mLoginBtn = findViewById(R.id.loginBtn);
        mCreateBtn = findViewById(R.id.createText);
        forgotTextLink = findViewById(R.id.forgotPassword);


        fAuth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();
        progressBar = findViewById(R.id.progressBar);


        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = mEmail.getText().toString().trim();
                final String password = mPassword.getText().toString().trim();


                progressBar.setVisibility(View.VISIBLE);


                Log.d(TAG, "starting collection"+" "+email+" "+password );
        firestore.collection("volunteer").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
//                    Log.d(TAG, "into the collection" );
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                Log.d(TAG, "into the collection" );
                if (task.isSuccessful()) {

                    for (QueryDocumentSnapshot document : task.getResult()) {
                        list.add(document.getId().toString());
                        firestore.collection("volunteer").document(document.getId()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                if (task.isSuccessful()) {
                                    DocumentSnapshot document = task.getResult();
                                    if (document.exists()) {

                                        String mail= document.getString("email");
                                        String password1= document.getString("password");
                                        System.out.println(mail);
                                        System.out.println(password1);
                                        Log.d(TAG, mail+"********** "+password1);
                                        Log.d(TAG, email+"********** "+password);
                                        if(mail!=null&&password1!=null&& mail.equals(email) && password.equals(password1))
                                        {
                                            Log.d(TAG, "going to home");
                                            startActivity(new Intent(vollogin.this,MainActivity.class));
                                        }
                                    }
                                }
                            }
                        });
                    }

                } else {
                    Log.d(TAG, "Error getting documents: ", task.getException());
                }
            }
        });
//             TextView phone;
//
//             int flag1 = 0;
                Log.d(TAG, "notinloop");
                Log.d(TAG, " this is list *******"+ list.size());
         for(int i=0;i<list.size();i++)
         {

             Log.d(TAG, "forllop");
             firestore.collection("volunteer").document(list.get(i)).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                 @Override
                 public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                     if (task.isSuccessful()) {
                         DocumentSnapshot document = task.getResult();
                         if (document.exists()) {

                            String mail= document.getString("email");
                             String password1= document.getString("password");
                             System.out.println(mail);
                             System.out.println(password1);
                             Log.d(TAG, mail+" "+password1);
                             if(mail==email&&password==password1)
                             {
                                 System.out.println(mail);
                                 System.out.println(password);
                                           flag =1;
                             }
                             }
                     }
                 }
             });



         }







    }
});

}

}