package com.example.sqllite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class deleteStudent extends AppCompatActivity {
    TextInputLayout usn;
    Button btn;
    DbHandler mydb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mydb=new DbHandler(this);
        setContentView(R.layout.activity_delete_student);
        usn=(TextInputLayout) findViewById(R.id.stdusn);
        btn=(Button)findViewById(R.id.btndelete);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer deletedRows=mydb.deletedata(usn.getEditText().getText().toString());
                if(deletedRows>0)
                    Toast.makeText(deleteStudent.this,"DATA DELETED",Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(deleteStudent.this,"DATA NOT DELETED",Toast.LENGTH_LONG).show();
            }
        });
    }
}
