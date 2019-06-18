package com.example.sqlite;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sqlite.SQliteListView.SQLiteList;

public class MainActivity extends AppCompatActivity {

    //Here in ths project we see how we add, delete , select and update data in SQLite database.
    //SQlite is light weight anroid inbuilt database..

    Button add,showview,update,delete,second; //create references our widgets
    DBManager dbManager; //create reference our database class
    EditText name,email,tvshow,idcon; ////create references our widgets
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbManager = new DBManager(MainActivity.this);
        //inisilize our class and here making the object implicitly call the constructor
        //and this will return our database access/.

        name= findViewById(R.id.name);
        email = findViewById(R.id.email);
        tvshow = findViewById(R.id.tvshow);
        add=findViewById(R.id.add);
        showview = findViewById(R.id.subtract);
        update = findViewById(R.id.update);
        delete = findViewById(R.id.del);
        idcon = findViewById(R.id.idcontain);
        second = findViewById(R.id.seo);

        //call the function
        setAdd();
        showData();
        updateData();
        deleteDataTable();

        second.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SQLiteList.class));
            }
        });
    }


    private void setAdd()
    {
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username = name.getText().toString().trim();
                String useremail = email.getText().toString().trim();
                String usertvshw = tvshow.getText().toString().trim();

                boolean add = dbManager.addData(username,useremail,usertvshw);
                //here after making the object refrence we call its instance method
                //addData and paas 3 actual arguments.

                if(add == true) {
                    Toast.makeText(MainActivity.this, "Data Successfully Added", Toast.LENGTH_SHORT).show();
                    name.setText("");
                    email.setText("");
                    tvshow.setText("");
                }
                else
                    Toast.makeText(MainActivity.this, "Data added failed", Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void showData() {

        showview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Cursor cursor = dbManager.showDataTable(); //when we run select query in any dataabse it will return a virtual table
                //here in SQLite we receive this in Cursor..

                if(cursor.getCount() == 0){ //cursor class method getCount() check in table rows available or not

                    showdataView("Error","No data found");

                }else{

                    StringBuffer Buffer = new StringBuffer(); //
                    //Java StringBuffer class is used to create mutable string in java.
                    // StringBuffer class is thread-safe multiple threads cannot access it simultaneously.
                    // So it is safe and will result in an order.

                    while (cursor.moveToNext()){

                        Buffer.append("ID: "+cursor.getString(0)+ "\n");
                        Buffer.append("Name: "+cursor.getString(1)+ "\n");
                        Buffer.append("Email: "+cursor.getString(2)+ "\n");
                        Buffer.append("TV Show: "+cursor.getString(3)+ "\n");

                        showdataView("All stored Data",Buffer.toString());
                    }
                }
            }
        });
    }

    private  void showdataView(String title , String meassge){

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(true);
                builder.setTitle(title);
                builder.setMessage(meassge);
                builder.show();
    }

    private void updateData() {


        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int temp = idcon.getText().toString().length();

                if(temp > 0){

                    String id = idcon.getText().toString().trim();
                    String username = name.getText().toString().trim();
                    String useremail = email.getText().toString().trim();
                    String usertvshw = tvshow.getText().toString().trim();

                    boolean flag = dbManager.setUpdateData(id,username,useremail,usertvshw);

                    if(flag == true){
                        idcon.setText("");
                        Toast.makeText(MainActivity.this, "Data update Successfully", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(MainActivity.this, "Data updation failed", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(MainActivity.this, "You must add a id to update the data", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void deleteDataTable(){

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int temp = idcon.getText().toString().length();

                if (temp > 0) {

                    String id = idcon.getText().toString().trim();
                    int x = dbManager.deleteData(id);
                    if(x > 0){
                        Toast.makeText(MainActivity.this, "Data Deleted", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(MainActivity.this, "Error Some problem", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(MainActivity.this, "You must enter ID for delete", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
