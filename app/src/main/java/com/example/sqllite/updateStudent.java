package com.example.sqllite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.google.android.material.textfield.TextInputLayout;

public class updateStudent extends AppCompatActivity {

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
        setContentView(R.layout.activity_update_student);
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
        ArrayAdapter<String> myAdapter=new ArrayAdapter<String>(updateStudent.this,R.layout.spinner_item,getResources().getStringArray(R.array.names));
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
                if(label1.trim().isEmpty()||label2.trim().isEmpty()||label3.trim().isEmpty()||label.trim().isEmpty()||label4.trim().isEmpty()||label5.trim().isEmpty()||label6.trim().isEmpty()){
                    Toast.makeText(updateStudent.this,"Feild is Empty",Toast.LENGTH_LONG).show();

                }
                else{
                    mydb.updateData(label,label1,label2,label3,label4,label5,label6);
                    Toast.makeText(updateStudent.this,"Data is Updated",Toast.LENGTH_LONG).show();

                }
            }
        });

    }

    public String RadioButtonClicked(View view) {
        radioButton=(RadioButton)view;
        String mySelectedId=radioButton.getText().toString();
        return  mySelectedId;
    }
}
