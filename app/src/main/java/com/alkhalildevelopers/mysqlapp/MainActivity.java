package com.alkhalildevelopers.mysqlapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    
    Button insertBtn,showDataBtn,updateBtn,deleteBtb;
    EditText nameT,surnameT,marksT,idT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final DatabaseHelper databaseHelper = new DatabaseHelper(this);
        databaseHelper.getWritableDatabase();
        
        nameT = findViewById(R.id.nameTxt);
        surnameT = findViewById(R.id.surNameTxt);
        marksT = findViewById(R.id.marksTxt);
        insertBtn = findViewById(R.id.insertBtn);
        showDataBtn = findViewById(R.id.showBtn);
        idT= findViewById(R.id.idTxt);
        deleteBtb = findViewById(R.id.deleteBtn);
        updateBtn = findViewById(R.id.updateBtn);

        //To Delete the Data
        deleteBtb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isDeleted = databaseHelper.deleteData(idT.getText().toString());
                if (isDeleted == true){
                    Toast.makeText(MainActivity.this, "Deleted Successfully", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(MainActivity.this, "Not Deleted", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //To Update the Data
        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               boolean isUpdated = databaseHelper.updateData(idT.getText().toString(),nameT.getText().toString(),surnameT.getText().toString(),marksT.getText().toString());
                if (isUpdated == true){
                    Toast.makeText(MainActivity.this, "Updated Successfully", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(MainActivity.this, "Not Updated", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //To inset/Add the Data
        insertBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              boolean inserted = databaseHelper.insertData(nameT.getText().toString(),surnameT.getText().toString(),marksT.getText().toString());
              if (inserted == true){
                  Toast.makeText(MainActivity.this, "DATA inserted successfully", Toast.LENGTH_SHORT).show();
              }else {
                  Toast.makeText(MainActivity.this, "DATA is not inserted", Toast.LENGTH_SHORT).show();
              }
            }
        });

        //To show/get the Data
        showDataBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor cursor = databaseHelper.getAllData();
                if (cursor.getCount() ==0){
                    showMessage("Student Results","Result are not available");
                    return;
                }
                StringBuffer stringBuffer = new StringBuffer();
                while ( cursor.moveToNext()){

                    stringBuffer.append("ID : " + cursor.getString(0) + "\n" );
                    stringBuffer.append("Name : " + cursor.getString(1) + "\n");
                    stringBuffer.append("Surname : " + cursor.getString(2) + "\n");
                    stringBuffer.append("Marks : " + cursor.getString(3) + "\n");


                }

                showMessage("Student Results",stringBuffer.toString());


            }
        });

    }

    //To Create a Coustom Dialog to show the data/message
    public void showMessage (String Title,String Message){

        AlertDialog.Builder dataMessage = new AlertDialog.Builder(this);
        dataMessage.setCancelable(true);
        dataMessage.setTitle(Title);
        dataMessage.setMessage(Message);
        dataMessage.setIcon(R.mipmap.ic_launcher_round);
        dataMessage.show();
    }

}
