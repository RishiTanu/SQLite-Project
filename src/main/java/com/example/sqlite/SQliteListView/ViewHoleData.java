package com.example.sqlite.SQliteListView;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.sqlite.R;

import java.util.ArrayList;

public class ViewHoleData extends AppCompatActivity {

    DBhelper dBhelper;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_hole_data);

       dBhelper = new DBhelper(ViewHoleData.this);
       listView = findViewById(R.id.list);

       populateData();

    }

    private void populateData()
    {
        final Cursor cursor = dBhelper.getData();
        ArrayList<String> arrayList = new ArrayList<String>();

        if(cursor.getCount() != 0){

            while (cursor.moveToNext()){ //get the valuefrom database in cloumn
                arrayList.add(cursor.getString(1)); //add the value in database
            }
        }
        else {
            Toast.makeText(this, "No Data found", Toast.LENGTH_SHORT).show();
        }

        final ListAdapter listAdapter = new ArrayAdapter<>(ViewHoleData.this,android.R.layout.simple_list_item_1,arrayList);
        listView.setAdapter(listAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

               String name = adapterView.getItemAtPosition(position).toString();
               //basically getItemAtPostion will return the object and we will convert into string/..

               Cursor cursor1= dBhelper.getSelectedNameId(name);
                int ID = -1;
               while (cursor1.moveToNext()) {
                   ID = cursor1.getInt(0);
               }

               if(ID > -1){

                   Intent a =new Intent(ViewHoleData.this,EditDataActivity.class);
                   a.putExtra("ID",ID);
                   a.putExtra("name",name);
                   startActivity(a);

               }else{
                   Toast.makeText(ViewHoleData.this, "No Id associated this name", Toast.LENGTH_SHORT).show();
               }

            }
        });
    }
}
