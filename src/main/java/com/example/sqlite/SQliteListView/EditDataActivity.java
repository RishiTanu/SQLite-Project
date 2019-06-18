package com.example.sqlite.SQliteListView;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sqlite.R;

public class EditDataActivity extends AppCompatActivity {

    EditText editText;
    Button del,save;
    DBhelper dBhelper;

    private int ID;
    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_data);

        dBhelper =  new DBhelper(EditDataActivity.this);

        editText = findViewById(R.id.setdynmic);
        del = findViewById(R.id.del);
        save = findViewById(R.id.save);

        ID = getIntent().getIntExtra("ID",-1);
        name = getIntent().getStringExtra("name");

        editText.setText(name);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = editText.getText().toString().trim();

                if(editText.length() > 0){

                    dBhelper.UpdateData(ID,name);

                }else{
                    Toast.makeText(EditDataActivity.this, "You must add some name", Toast.LENGTH_SHORT).show();
                }

            }
        });

        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                dBhelper.deleData(ID,name);
                editText.setText("");
                Toast.makeText(EditDataActivity.this, "Remove from database", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
