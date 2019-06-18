package com.example.sqlite.SQliteListView;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sqlite.R;

public class SQLiteList extends AppCompatActivity {

    EditText text;
    DBhelper dBhelper;
    Button add,view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite_list);

        text = findViewById(R.id.text);
        add= findViewById(R.id.adddata);
        view =  findViewById(R.id.viewdata);

        dBhelper = new DBhelper(SQLiteList.this);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String textstring = text.getText().toString().trim();

                if(text.length() != 0){
                    ADDDATA(textstring);
                    text.setText("");
                }else{
                    Toast.makeText(SQLiteList.this, "You must add some data", Toast.LENGTH_SHORT).show();
                }
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SQLiteList.this,ViewHoleData.class));
                finish();
            }
        });


    }

    private void ADDDATA(String item) {

       boolean x = dBhelper.addData(item);
       if(x)
           Toast.makeText(this, "Data inserted", Toast.LENGTH_SHORT).show();
        else
           Toast.makeText(this, "Data not inserted", Toast.LENGTH_SHORT).show();
    }


}
