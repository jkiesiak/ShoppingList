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
import java.util.List;

public class ListViewLists extends AppCompatActivity {

    MyDataBaseClass mDatabase;
    private ListView mListView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_layout);
        mListView = (ListView) findViewById(R.id.listView);
        mDatabase = new MyDataBaseClass(this);
        showLists();


    }

    private void showLists() {

        Cursor data = mDatabase.viewLists();
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
                    Cursor data = mDatabase.getListID(name); //// id wybranej listy
                    int itemID = -1;
                    while (data.moveToNext()) {
                        itemID = data.getInt(0);
                    }
                    if (itemID > -1) {

                        ////////////////////aktywnosc DeleteIngriedient go EDITTTT
                        Intent editScreenIntent = new Intent(ListViewLists.this, DeleteList.class);
                        editScreenIntent.putExtra("id", itemID);
                        editScreenIntent.putExtra("list", name);
                        startActivity(editScreenIntent);
                    } else {

                        Toast.makeText(ListViewLists.this, "No ID class:ListListViewLists", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(ListViewLists.this, "exeption picked list" + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });


    }


}
