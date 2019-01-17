package com.example.android.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, View.OnKeyListener{

    private String username,password,email;
    private DatabaseReference databaseFarmer;
    private FirebaseAuth firebaseAuth;
    private TextView changeSignupmode;
    private EditText pwdET;
    private Boolean signUpModeActive = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firebaseAuth = FirebaseAuth.getInstance();
        databaseFarmer = FirebaseDatabase.getInstance().getReference("Farmer");

        changeSignupmode= (TextView) findViewById(R.id.changeSignupmodeTextview);
        changeSignupmode.setOnClickListener(this);

        pwdET=(EditText) findViewById(R.id.pw);

        //for touchinng in bg ..keypad should be invisible
        LinearLayout linearLayout=(LinearLayout) findViewById(R.id.rl);
        ImageView imageView=(ImageView) findViewById(R.id.logo);

        linearLayout.setOnClickListener(this);
        imageView.setOnClickListener(this);


        pwdET.setOnKeyListener( this);
    }

    @Override
    public void onClick(View v) {

        if(v.getId()==R.id.changeSignupmodeTextview){
            Log.i("App info","Change  signup mode");
            Button mainButton=(Button) findViewById(R.id.MainButton);


            if(signUpModeActive){
                signUpModeActive=false;
                mainButton.setText("Login");
                changeSignupmode.setText("Or,Signup");

            }
            else{
                signUpModeActive=true;
                mainButton.setText("Signup");
                changeSignupmode.setText("Or, Login");
            }
        }

        // for touchinng in bg ..keypad should be invisible
        else if(v.getId()==R.id.rl || v.getId()==R.id.logo){
            InputMethodManager inputMethodManager=(InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),0);
        }
    }
    public void signUp(View view){
        EditText usernameET=(EditText) findViewById(R.id.un);
        username = usernameET.getText().toString().trim();
        password = pwdET.getText().toString().trim();
        if(usernameET.getText().toString().matches("") || pwdET.getText().toString().matches("")){
            Toast.makeText(this,"Required Username / Password",Toast.LENGTH_LONG).show();
        }
        else{
            if(signUpModeActive){
                CreateUser();
            }
            else {
                LoginUser();
            }

        }
    }
    @Override
    public boolean onKey(View view, int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_ENTER && event.getAction()==KeyEvent.ACTION_DOWN){
            signUp(view);
        }
        return false;
    }
    public void CreateUser()
    {
        firebaseAuth.createUserWithEmailAndPassword(username,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            finish();
                            int in=username.indexOf('@');
                            String pemail=username.replace(".com","");
                            pemail=pemail.replace(".","_");
                            //String pname =username.substring(0,in);
                            //databaseFarmer.child(pemail).setValue(farmer);
                            Toast.makeText(getApplicationContext(),"Registration successful",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),UserActivity.class));
                        }else{
                            Toast.makeText(MainActivity.this,"Registration unsuccessful,  Please try again",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
    public void LoginUser()
    {
        firebaseAuth.signInWithEmailAndPassword(username,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            //start the activity
                            finish();
                            startActivity(new Intent(getApplicationContext(),UserActivity.class));
                        }
                    }
                });
    }
}
