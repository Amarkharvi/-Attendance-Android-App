package com.example.sqllite;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;

import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity {
    TextInputLayout Username,Password;
    Button login_btn;
    TextView register;
    DbHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        db=new DbHandler(this);
        Username=(TextInputLayout)findViewById(R.id.userlogin);
        Password=(TextInputLayout)findViewById(R.id.userpass);
        login_btn=(Button)findViewById(R.id.loginbtn);
        register=(TextView)findViewById(R.id.backtoregister);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Login.this,Register.class);
                startActivity(intent);
            }
        });
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String label1=Username.getEditText().getText().toString().trim();
                String label2=Password.getEditText().getText().toString().trim();
                Boolean res=db.checkUser(label1,label2);
                if(res==true){
                    Snackbar.make(login_btn,"Login Successfull",Snackbar.LENGTH_LONG).show();
                    Intent intent=new Intent(Login.this,Home.class);
                    startActivity(intent);
                }
                else{
                    Snackbar.make(login_btn,"Password or User-Name is Incorrect",Snackbar.LENGTH_LONG).show();
                }

            }
        });

    }
    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        Intent intent=new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
        System.exit(0);
    }
}
