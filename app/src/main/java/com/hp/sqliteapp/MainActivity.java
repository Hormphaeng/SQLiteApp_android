package com.hp.sqliteapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText Id, Name, Surname, Tel, Email;
    Button insert, update, delete, view;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Id = findViewById(R.id.id);
        Name = findViewById(R.id.name);
        Surname = findViewById(R.id.sur);
        Tel = findViewById(R.id.tel);
        Email = findViewById(R.id.email);

        insert = findViewById(R.id.btnInsert);
        update = findViewById(R.id.btnUpdate);
        delete = findViewById(R.id.btnDelete);
        view = findViewById(R.id.btnView);
        DB = new DBHelper(this);

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String idTXT = Id.getText().toString();
                String nameTXT = Name.getText().toString();
                String surnameTXT = Surname.getText().toString();
                String telTXT = Tel.getText().toString();
                String emailTXT = Email.getText().toString();

                Boolean checkinsertdata = DB.insertemployeedata(idTXT, nameTXT, surnameTXT, telTXT, emailTXT);
                if (checkinsertdata == true )
                    Toast.makeText(MainActivity.this, "New Data Inserted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "Insert new Data Fail!!", Toast.LENGTH_SHORT).show();
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String idTXT = Id.getText().toString();
                String nameTXT = Name.getText().toString();
                String surnameTXT = Surname.getText().toString();
                String telTXT = Tel.getText().toString();
                String emailTXT = Email.getText().toString();

                Boolean checkupdatedata = DB.updateemployeedata(idTXT, nameTXT, surnameTXT, telTXT, emailTXT);
                if (checkupdatedata == true )
                    Toast.makeText(MainActivity.this, "Updated..", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "Update Data Fail!!", Toast.LENGTH_SHORT).show();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String idTXT = Id.getText().toString();

                Boolean checkdeletedata = DB.deletedata(idTXT);
                if (checkdeletedata == true )
                    Toast.makeText(MainActivity.this, "Deleted..", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "Delete Data Fail!!", Toast.LENGTH_SHORT).show();
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res = DB.getdata();
                if (res.getCount() == 0){
                    Toast.makeText(MainActivity.this, "No Data", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()){
                    buffer.append("Id:"+res.getString(0)+"\n");
                    buffer.append("Name:"+res.getString(1)+"\n");
                    buffer.append("Surname:"+res.getString(2)+"\n");
                    buffer.append("Tel:"+res.getString(3)+"\n");
                    buffer.append("Email:"+res.getString(4)+"\n\n");
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(true);
                builder.setTitle("Employees Data");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });
    }
}