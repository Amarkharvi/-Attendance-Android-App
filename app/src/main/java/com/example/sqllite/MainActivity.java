package com.example.sqllite;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;

public  class MainActivity extends AppCompatActivity {
    TextInputLayout name,usn,phone,sem,sec;
    RadioGroup radioGroup;
    RadioButton radioButton;
    Spinner spinner;
    Button button;
    DbHandler mydb;
    FloatingActionMenu floatingActionMenu;
    FloatingActionButton deletedata,updatedata,viewdata;
    String label1,label2,label3,label,label4,label5,label6;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mydb=new DbHandler(this);
        usn=(TextInputLayout) findViewById(R.id.stdusn);
        name=(TextInputLayout) findViewById(R.id.stdname);
        button=(Button)findViewById(R.id.btn);
        phone=(TextInputLayout) findViewById(R.id.stdphone);
        sem=(TextInputLayout) findViewById(R.id.stdsem);
        sec=(TextInputLayout) findViewById(R.id.stdsec);
        spinner=(Spinner)findViewById(R.id.spinner1);
        radioGroup=(RadioGroup)findViewById(R.id.radiogrp);
        floatingActionMenu=(FloatingActionMenu)findViewById(R.id.floatingmenu);
        deletedata=(FloatingActionButton)findViewById(R.id.deletedata);
        updatedata=(FloatingActionButton)findViewById(R.id.updatedata);
        viewdata=(FloatingActionButton)findViewById(R.id.viewdata);
        ArrayAdapter<String> myAdapter=new ArrayAdapter<String>(MainActivity.this,R.layout.spinner_item,getResources().getStringArray(R.array.names));
        myAdapter.setDropDownViewResource(R.layout.spinner_item);
        spinner.setAdapter(myAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                label3=adapterView.getItemAtPosition(i).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                label=usn.getEditText().getText().toString();
                label1=name.getEditText().getText().toString();
                label4=phone.getEditText().getText().toString();
                label5=sem.getEditText().getText().toString();
                label6=sec.getEditText().getText().toString();
                int id=radioGroup.getCheckedRadioButtonId();
                label2=RadioButtonClicked(findViewById(id));
                if((label1.trim().isEmpty() || label2.trim().isEmpty() || label3.trim().isEmpty() || label.trim().isEmpty() || label4.trim().isEmpty() ||
                        label5.trim().isEmpty() || label6.trim().isEmpty()) ||
                        (label.trim().isEmpty() && label1.trim().isEmpty() && label2.trim().isEmpty() && label3.trim().isEmpty() && label4.trim().isEmpty() &&
                                label5.trim().isEmpty() && label6.trim().isEmpty()))
                {
                    Snackbar.make(button,"Some Fields are Empty",Snackbar.LENGTH_LONG).show();


                }
                else {

                    mydb.insertData(label,label1,label2,label3,label4,label5,label6);
                    Snackbar.make(button,"Data Inserted!!",Snackbar.LENGTH_LONG).show();
                }
            }
        });

        tableProperties();




    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        Intent intent=new Intent(MainActivity.this,Home.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    public void tableProperties(){
        deletedata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,deleteStudent.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);

                startActivity(intent);

            }
        });
        updatedata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,updateStudent.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);

                startActivity(intent);
            }
        });

        viewdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res=mydb.getAllData();
                if(res.getCount() == 0){
                    showMessage("Error","No Data Found!!");
                    return;
                }
                StringBuffer buffer=new StringBuffer();
                while(res.moveToNext()){
                    buffer.append("USN:  "+ res.getString(0)+"\n");
                    buffer.append("Student Name:  "+res.getString(1)+"\n");
                    buffer.append("Gender:  "+res.getString(2)+"\n");
                    buffer.append("Branch:  "+res.getString(3)+"\n");
                    buffer.append("Phone No:  "+res.getString(4)+"\n");
                    buffer.append("Sem:  "+res.getString(5)+"\n");
                    buffer.append("Sec:  "+res.getString(6)+"\n-----------------------------------\n");



                }
                showMessage("Student Details:",buffer.toString());
            }
        });


    }
    public void showMessage(String title,String Message){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true );
        builder.setMessage(Message);
        builder.setTitle(title);
        builder.show();

    }


    public String RadioButtonClicked(View view) {
        radioButton=(RadioButton)view;
        String mySelectedId=radioButton.getText().toString();
        return  mySelectedId;
    }

}
