package com.example.test;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddList extends AppCompatActivity {

    MyDataBaseClass objMyDataBaseClass;
    Button button_add_list, next, show_lists_from_db;
    EditText edit_text_name_list, edit_text_name_list_type;
    TextView insertedLists;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_list_name);

        objMyDataBaseClass = new MyDataBaseClass(this);

        edit_text_name_list = findViewById(R.id.edit_text_name_list);
        button_add_list = findViewById(R.id.create_list);
        insertedLists = findViewById(R.id.insertedLists);
        edit_text_name_list_type = findViewById(R.id.edit_text_name_list_type);
        next = findViewById(R.id.next);
        show_lists_from_db = findViewById(R.id.show_lists_from_db);

        //////dodajemy skladniki
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addIngriedientsToList();
            }
        });

        show_lists_from_db.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listAllIngriedients();
            }
        });
    }


    public void createDatabase(View view) {
        try {
            objMyDataBaseClass.getReadableDatabase();
            Toast.makeText(this, "Created db", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Toast.makeText(this, "exeption Created db" + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public void insertListIntoDatabase(View view) {
        try {
            SQLiteDatabase objSqLiteDatabase = objMyDataBaseClass.getWritableDatabase();
            if (objSqLiteDatabase != null) {
                if (!edit_text_name_list.getText().toString().isEmpty() && !edit_text_name_list_type.getText().toString().isEmpty()) {
                    ContentValues objContentValuess = new ContentValues();
                    objContentValuess.put("List_name", edit_text_name_list.getText().toString());
                    objContentValuess.put("lista_typ", edit_text_name_list_type.getText().toString());
                    long checkIfQueryRuns = objSqLiteDatabase.insert("ListOfLists", null, objContentValuess);
                    if (checkIfQueryRuns != -1) {
                        Toast.makeText(this, "List inserted", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(this, "Please fill name of list", Toast.LENGTH_LONG).show();
                }
            } else {
                Toast.makeText(this, "Created db", Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Toast.makeText(this, "exeption insert db list" + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private void listAllIngriedients() {
        Intent intent4 = new Intent(AddList.this, ListViewLists.class);
        startActivity(intent4);
    }
    ///////////////////////////////////////// koment tej funkcjonalnosci
//    public void showListFromDatabase(View view) {
//        try {
//            SQLiteDatabase objSqLiteDatabase = objMyDataBaseClass.getWritableDatabase();
//            if (objSqLiteDatabase != null) {
//                Cursor objCursor = objSqLiteDatabase.rawQuery("select * from ListOfLists order by COLUMN_ID", null);
//                StringBuffer stringBuffer = new StringBuffer();
//                if (objCursor.getCount() == 0) {
//                    Toast.makeText(this, "no data in table lists", Toast.LENGTH_LONG).show();
//                } else {
//                    while (objCursor.moveToNext()) {
//                        stringBuffer.append("ID: " + objCursor.getInt(0)+ "     ");
//                        stringBuffer.append("Name:" + objCursor.getString(1) + "   ");
//                        stringBuffer.append("Type:" + objCursor.getString(2) + "\n");
//                    }
//                    insertedLists.setText(stringBuffer);
//                }
//            } else {
//                Toast.makeText(this, "database/list is null", Toast.LENGTH_LONG).show();
//            }
//        } catch (Exception e) {
//            Toast.makeText(this, "exeption showing db/list" + e.getMessage(), Toast.LENGTH_LONG).show();
//        }
//    }

    private void addIngriedientsToList() {
        Intent intent = new Intent(AddList.this, AddIngriedients.class);
        startActivity(intent);
    }

}
