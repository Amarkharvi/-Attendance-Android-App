package com.example.sqllite;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;

import androidx.appcompat.app.AppCompatActivity;

public class Register extends AppCompatActivity {
    TextInputLayout Username,Password,cnfpassword;
    Button btn;
    TextView backTologin;
    DbHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        db=new DbHandler(this);
        Username=(TextInputLayout)findViewById(R.id.usernamereg);
        Password=(TextInputLayout)findViewById(R.id.passwordreg);
        cnfpassword=(TextInputLayout)findViewById(R.id.cnf_pwd);
        btn=(Button)findViewById(R.id.btnregister);
        backTologin=(TextView)findViewById(R.id.backtologin);
        backTologin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Register.this,Login.class);
                startActivity(intent);
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String label1=Username.getEditText().getText().toString();
                String label2=Password.getEditText().getText().toString();
                String label3=cnfpassword.getEditText().getText().toString();

                if(label2.equals(label3)){
                    boolean val=db.addUsers(label1,label2);
                    if(val==true){
                        Snackbar.make(btn,"Successfully Registered",Snackbar.LENGTH_LONG).show();
                    }
                    else{
                        Snackbar.make(btn," Registeration Error",Snackbar.LENGTH_LONG).show();
                    }
                }
                else{
                    Snackbar.make(btn,"Password Don't Match",Snackbar.LENGTH_LONG).show();
                }

            }
        });
    }
    public void onBackPressed() {
        //super.onBackPressed();
        Intent intent=new Intent(this,Login.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
