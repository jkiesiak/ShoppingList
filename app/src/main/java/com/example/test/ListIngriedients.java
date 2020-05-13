package com.example.test;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ListIngriedients extends AppCompatActivity {
//    private static final String TAG = "ListDataActivity";

    MyDataBaseClass mDatabase;
    private ListView mListView;
    private String selectedList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_layout);
        mListView = (ListView) findViewById(R.id.listView);
        mDatabase = new MyDataBaseClass(this);
        Intent receivedIntent = getIntent();
        selectedList = receivedIntent.getStringExtra("lista_przynaleznosc");

        showIngriedientsFromList();
    }

    private void showIngriedientsFromList() {


        Cursor data = mDatabase.viewIngriedients(selectedList);
        ArrayList<String> listData = new ArrayList<>();
        while (data.moveToNext()) {
            listData.add(data.getString(1));
        }
        ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listData);
        mListView.setAdapter(adapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                try {
                    String name = adapterView.getItemAtPosition(i).toString();
//                    Log.d(TAG, "onItemClick: You Clicked on " + name);

                    Cursor data = mDatabase.getItemID(name); //// id wybranego ingriedientu
                    int itemID = -1;
                    while (data.moveToNext()) {
                        itemID = data.getInt(0);
                    }
                    if (itemID > -1) {
//                        Log.d(TAG, "onItemClick: The ID is: " + itemID);
                        ////////////////////aktywnosc DeleteIngriedient go
                        Intent editScreenIntent = new Intent(ListIngriedients.this, DeleteIngriedient.class);
                        editScreenIntent.putExtra("id", itemID);
                        editScreenIntent.putExtra("name", name);
                        startActivity(editScreenIntent);
                    } else {

                        Toast.makeText(ListIngriedients.this, "No ID class:ListIngriedients", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(ListIngriedients.this, "exeption picked ingriedient" + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}